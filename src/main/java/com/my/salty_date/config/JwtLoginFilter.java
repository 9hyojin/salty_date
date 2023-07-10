package com.my.salty_date.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.salty_date.config.jwt.JwtUtil;
import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl("/members/login");
    }

    //사용자인증처리
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            MemberRequest memberRequest = objectMapper.readValue(request.getInputStream(), MemberRequest.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    memberRequest.getEmail(), memberRequest.getPassword(),null);
            return getAuthenticationManager().authenticate(token);

        } catch (IOException e) {
            // 예외 처리
            logger.error("Error occurred while parsing JSON data from request.", e);
            // 오류 응답 보내기 등의 동작 수행
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Member member = (Member) authResult.getPrincipal();
        response.setHeader(HttpHeaders.AUTHORIZATION,"Bearer"+ JwtUtil.makeAuthToken(member));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(member));
    }
}
