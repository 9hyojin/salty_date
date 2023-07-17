package com.my.salty_date.service;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
import com.my.salty_date.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncode;

    public Long save(MemberRequest request) {
        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(bCryptPasswordEncode.encode(request.getPassword()))
                .build()).getMemIdx();
    }
        public Member findByMemIdx(Long memIdx) {
        return memberRepository.findById(memIdx)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }


}







