package com.my.salty_date.service;

import com.my.salty_date.config.Bcrypt;
import com.my.salty_date.config.jwt.TokenProvider;
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
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(MemberRequest request) {
        return memberRepository.save(Member.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build()).getMemIdx();
    }


    public Member findById(Long memIdx) {
        return memberRepository.findById(memIdx)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}





//    public boolean checkEmailDuplication(String email) {
//         return memberRepository.existsByEmail(email);
//
//    }




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




