package com.my.salty_date.controller;

import com.my.salty_date.dto.MemberFormDto;
import com.my.salty_date.entity.Member;
import com.my.salty_date.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto",new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("new")
    public String memberForm(MemberFormDto memberFormDto){
        Member member = Member.createMember(memberFormDto,passwordEncoder);
        memberService.saveMember(member);
        return "redirect:/";
    }

}
