package com.my.salty_date.config.jwt;

import com.my.salty_date.entity.Member;
import com.my.salty_date.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtProperties jwtProperties;



    @DisplayName("generateToken 검증테스트")
    @Test
    void generateToken(){
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        //given
        Member testMember = memberRepository.save(Member.builder()
                        .email("123123qweqwe@test.com")
                        .password("testqweqwe123123123")
                        .name("구구구qweqwe")
                        .build());
        //when
        String token = tokenProvider.generateToken(testMember, Duration.ofDays(14));

        //then
        Long memIdx = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("memIdx", Long.class);

        assertThat(memIdx).isEqualTo(testMember.getMemIdx());

    }



    @DisplayName("validToken 검증테스트 - 실패")
    @Test
    void validToken_invalidToken(){
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(jwtProperties);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();
    }


    @DisplayName("validToken 검증테스트 - 성공")
    @Test
    void validToken_validToken(){
        //given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isTrue();
    }



    @DisplayName("getAuthentication 검증테스트")
    @Test
    void getAuthentication(){
        //given
        String memberEmail = "member@email.com";
        String token = JwtFactory.builder().subject(memberEmail).build().createToken(jwtProperties);

        //when
        Authentication authentication = tokenProvider.getAuthentication(token);

        //then
        assertThat(((UserDetails)authentication.getPrincipal()).getUsername()).isEqualTo(memberEmail);
    }

    @DisplayName("getUserId 검증테스트")
    @Test
    void getUserId(){
        //given
        Long memIdx = 1L;
        String token = JwtFactory.builder()
                .claims(Map.of("memIdx",memIdx))
                .build()
                .createToken(jwtProperties);

        //when
        Long memIdxByToken = tokenProvider.getUserId(token);

        //then
        assertThat(memIdxByToken).isEqualTo(memIdx);
    }

}