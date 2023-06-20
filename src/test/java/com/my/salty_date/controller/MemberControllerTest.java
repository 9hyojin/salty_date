package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
import com.my.salty_date.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password){
        MemberRequest memberFormDto = new MemberRequest();
        memberFormDto.setEmail(email);
        memberFormDto.setName("홍길동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto, passwordEncoder);

        return memberService.saveMember(member);
    }


    @Test
    @DisplayName("로그인성공")
    public void loginSuccessTest() throws Exception{

        String email = "test@email.com";
        String password = "123456789";
        this.createMember(email,password);

        //when
        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password(password))
        //then
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }


    @Test
    @DisplayName("로그인실패")
    public void loginFailTest() throws Exception{
        String email = "test@email.com";
        String password = "123456789";
        this.createMember(email,password);

        mockMvc.perform(formLogin().userParameter("email")
                .loginProcessingUrl("/members/login")
                .user(email).password("123123123"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }


}