package com.my.salty_date.service;

import com.my.salty_date.config.jwt.TokenProvider;
import com.my.salty_date.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long memIdx = refreshTokenService.findByRefreshToken(refreshToken).getMemIdx();
        Member member = memberService.findByMemIdx(memIdx);

        return tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}

