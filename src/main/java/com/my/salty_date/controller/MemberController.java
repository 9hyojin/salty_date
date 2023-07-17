package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


    @ResponseBody
    @GetMapping("/auth")
    public Authentication auth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping("/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }



    @GetMapping("/signUp")
    public String memberForm(Model model){
        model.addAttribute("MemberRequest", MemberRequest.builder().build());
        return "member/memberForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("MemberRequest") MemberRequest memberRequest, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try {
            memberService.save(memberRequest);
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
        return "redirect:/";
    }


}


