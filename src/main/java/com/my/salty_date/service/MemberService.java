package com.my.salty_date.service;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
import com.my.salty_date.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public Long save(MemberRequest request) {
        if (isEmailAlreadyExists(request.getEmail())) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .build()).getMemIdx();
    }

    private boolean isEmailAlreadyExists(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        return findMember.isPresent();
    }



    public Member findById(Long memIdx) {
        return memberRepository.findById(memIdx)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}


//    public Member saveMember(Member member) {
//        validateDuplicateMember(member);
//        return memberRepository.save(member);
//
//    }

//    private void validateDuplicateMember(Member member) {
//        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
//        if (findMember.isPresent()) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }



