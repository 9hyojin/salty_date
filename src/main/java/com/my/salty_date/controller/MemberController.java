package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
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

//    @PostMapping("/login")
//    public String login(MemberRequest memberRequest) {
//        memberService.login(memberRequest);
//        return "redirect:/";
//    }

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

//    @GetMapping("/signUp/{email}/exists")
//    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
//        return ResponseEntity.ok(memberService.checkEmailDuplication(email));
//    }

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
