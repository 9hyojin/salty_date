package com.my.salty_date.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.my.salty_date.entity.Member;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");
    private static final long AUTH_TIME = 20*60;  //20분 유효시간
    private static final long REFRESH_TIME = 60*60*24*7;  //1주일 유효기간
    public static String makeAuthToken(Member member){
        return JWT.create()
                .withSubject(member.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() +AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String makeRefreshToken(Member member){
        return JWT.create()
                .withSubject(member.getEmail())
                .withClaim("exp", Instant.now().getEpochSecond() +REFRESH_TIME)
                .sign(ALGORITHM);
    }


    //유효성검사
    public static VerifyResult veryfy(String token){
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return VerifyResult.builder()
                    .success(true)
                    .memEmail(verify.getSubject())
                    .build();

        }catch (Exception e){
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder()
                    .success(false)
                    .memEmail(decode.getSubject())
                    .build();
        }

    }

}
