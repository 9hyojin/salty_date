package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/members")
@RequiredArgsConstructor
@Controller

public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("MemberRequest", new MemberRequest());
        return "member/memberForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid MemberRequest request, BindingResult bindingResult, Model model){
            if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try {
            memberService.save(request);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        return "redirect:/members/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMSG","아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/members/login";
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
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(Member member){
//        return ResponseEntity.ok().body(memberService.login(member,""));
//    }
//
//}
