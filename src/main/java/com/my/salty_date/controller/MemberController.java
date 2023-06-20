package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@RequiredArgsConstructor
@Controller

public class MemberController {

    private final MemberService memberService;


        @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("signUpForm",new MemberRequest());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String signUp(MemberRequest request){
        memberService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }








}


//    @GetMapping("/new")
//    public String memberForm(Model model){
//        model.addAttribute("memberFormDto",new MemberFormDto());
//        return "member/memberForm";
//    }
//
//    @PostMapping("/new")
//    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
//        if(bindingResult.hasErrors()){
//            return "member/memberForm";
//        }
//        try {
//            Member member = Member.createMember(memberFormDto,passwordEncoder);
//            memberService.saveMember(member);
//        }catch (IllegalStateException e){
//            model.addAttribute("errorMessage", e.getMessage());
//            return "member/memberForm";
//        }
//
//        return "redirect:/";
//    }
//
//    @GetMapping("/login")
//    public String loginMember(){
//        return "/member/memberLoginForm";
//    }
//
//    @GetMapping("/login/error")
//    public String loginError(Model model){
//        model.addAttribute("loginErrorMSG","아이디 또는 비밀번호를 확인해주세요");
//        return "/member/memberLoginForm";
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(Member member){
//        return ResponseEntity.ok().body(memberService.login(member,""));
//    }
//
//}
