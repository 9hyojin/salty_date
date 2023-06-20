package com.my.salty_date.service;

import com.my.salty_date.entity.Member;
import com.my.salty_date.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public Member loadUserByUsername(String email){
        return memberRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException(email));

    }

}
