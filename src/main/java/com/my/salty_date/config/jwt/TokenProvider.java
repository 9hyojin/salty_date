package com.my.salty_date.config.jwt;

import com.my.salty_date.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;




    public String generateToken(Member member, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), member);
    }



    //JWT 생성 메서드
    private String makeToken(Date expiry, Member member ) {
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(member.getEmail())
                .claim("memIdx",member.getMemIdx())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    //JWT 유효성 검증 메서드
    public boolean validToken(String token){
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)   //비밀값으로 복호화
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e){     //복호화과정에서 에러가 나면 유효하지않은 토큰
            return false;
        }
    }


    //토큰기반 인증 정보를 가져오는 메소드
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.
                security.core.userdetails.User(claims.getSubject(),"",authorities),token,authorities);
    }


    //토큰기반 유저ID 가져오는 메서드
    public Long getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("memIdx", Long.class);
    }


    private Claims getClaims(String token) {
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}






//유튜브버전
//    public static String makeToken(Member member, String secretKey, Long expiredMs) {
//
//        Claims claims = Jwts.claims();
//        claims.put("memberName", member.getName());
//        byte[] keyBytes = secretKey.getBytes();
//        Key key = Keys.hmacShaKeyFor(keyBytes);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
//                .signWith(key)
//                .compact();
//    }


