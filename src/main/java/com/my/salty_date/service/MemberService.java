package com.my.salty_date.service;

import com.my.salty_date.dto.MemberRequest;
import com.my.salty_date.entity.Member;
import com.my.salty_date.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .name(request.getName())
                .build()).getMemIdx();
    }


        public Member findById(Long memIdx) {
        return memberRepository.findById(memIdx)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }


//    public boolean checkEmailDuplication(String email) {
//         return memberRepository.existsByEmail(email);
//
//    }


}




//    private boolean isEmailAlreadyExists(String email) {
//        Optional<Member> findMember = memberRepository.findByEmail(email);
//        return findMember.isPresent();
//    }
//
//    private void validateDuplicateMember(MemberRequest request) {
//        Optional<Member> findMember = memberRepository.findByEmail(request.getEmail());
//        if (findMember.isPresent()) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }





//    public Member saveMember(Member member) {
//        validateDuplicateMember(member);
//        return memberRepository.save(member);
//
//    }




