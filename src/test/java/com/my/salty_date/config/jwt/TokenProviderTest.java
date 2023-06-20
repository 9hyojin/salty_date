//package com.my.salty_date.config.jwt;
//
//import com.my.salty_date.entity.Member;
//import com.my.salty_date.repository.MemberRepository;
//import io.jsonwebtoken.Jwts;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//import java.time.Duration;
//import java.util.Date;
//import java.util.Map;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@SpringBootTest
//class TokenProviderTest {
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//
//
//    //generateToken() 검증 테스트
//    @DisplayName("generateToken(): 유저정보+만료기간")
//    @Test
//    void generateToken(){
//        //given
//        Member testMember = memberRepository.save(Member.builder()
//                .email("member@email.com")
//                .password("test")
//                .name("홍길동")
//                .build());
//
//        //when
//        String token = tokenProvider.generateToken(testMember, Duration.ofDays(14));
//
//        //then
//        Long memberId = Jwts.parserBuilder()
//                .setSigningKey(jwtProperties.getSecretKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("idx", Long.class);
//        assertThat(memberId).isEqualTo(testMember.getMemIdx());
//    }
//
//
//    @DisplayName("validToken() 유효하지 않은 토큰")
//    @Test
//    void validToken_inValidToken(){
//
//        //given
//        String token = JwtFactory.builder()
//                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
//                .build()
//                .createToken(jwtProperties);
//        //when
//        boolean result = tokenProvider.validToken(token);
//        //then
//        assertThat(result).isFalse();
//    }
//
//
//    @DisplayName("validToken() 유효한 토큰")
//    @Test
//    void validToken_ValidToken(){
//
//        //given
//        String token = JwtFactory.withDefaultValues()
//                .createToken(jwtProperties);
//        //when
//        boolean result = tokenProvider.validToken(token);
//        //then
//        assertThat(result).isTrue();
//    }
//
//    //getAuthentication() 검증테스트
//    @DisplayName("getAuthentication(): 토큰기반으로 인증정보 가져옴")
//    @Test
//    void getAuthentication(){
//
//        //given
//        String memberEmail = "member@email.com";
//        String token = JwtFactory.builder()
//                .subject(memberEmail)
//                .build()
//                .createToken(jwtProperties);
//        //when
//        Authentication authentication = tokenProvider.getAuthentication(token);
//        //then
//        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(memberEmail);
//    }
//
//    //getMemberIdx 검증테스트
//    @DisplayName("getMemberIdx() 토큰에서 멤버 ID 가져오기")
//    @Test
//    void getMemberIdx(){
//        //given
//        Long memberIdx = 1L;
//        String token = JwtFactory.builder()
//                .claims(Map.of("idx",memberIdx))
//                .build()
//                .createToken(jwtProperties);
//        //when
//        Long memberIdxByToken = tokenProvider.getUserId(token);
//        //then
//        assertThat(memberIdxByToken).isEqualTo(memberIdx);
//    }
////
//
//
//
//}