//package com.my.salty_date.config.jwt;
//
//import com.my.salty_date.dto.MemberRequest;
//import com.my.salty_date.entity.Member;
//import com.my.salty_date.repository.MemberRepository;
//import com.my.salty_date.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//
//@RequiredArgsConstructor
//@SpringBootTest
//public class JWTRequestTest {
//
//    private final MemberService memberService;
//    private final MemberRepository memberRepository;
//
//    @BeforeEach
//    void before(){
//        memberRepository.deleteAll();
//        Member member = memberService.save(MemberRequest.builder()
//                .email("test@test.com")
//                .password("123123123")
//                .build());
//    }
//
//    @DisplayName("1. detail 페이지 접근")
//    @Test
//    void test_1(){
//
//        RestTemplate client = new RestTemplate();
//        HttpEntity<MemberRequest> body = new HttpEntity<>(
//                MemberRequest.builder()
//                        .email("test@test.com")
//                        .password("123123123")
//                        .build()
//        );
//
//        ResponseEntity<Member> resp1 = client.exchange(("/members/login"), HttpMethod.POST, body, Member.class);
//
//
//    }
//
//}
