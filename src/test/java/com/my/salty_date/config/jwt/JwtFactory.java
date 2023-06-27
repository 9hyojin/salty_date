package com.my.salty_date.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;

import java.security.Key;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Getter
public class JwtFactory {
    private String subject = "test@email.com";
    private Date issuedAt = new Date();
    private Date expiration = new Date(new Date().getTime() + Duration.ofDays(14).toMillis());
    private Map<String, Object> claims = Collections.emptyMap();

    //빌더패턴을 사용해서 설정이 필요한 데이터만 선택설정
    @Builder
    public JwtFactory(String subject, Date issuedAt, Date expiration, Map<String,Object>claims){
        this.subject = subject != null ? subject : this.subject;
        this.issuedAt = issuedAt != null ? issuedAt : this.issuedAt;
        this.expiration = expiration != null ? expiration : this.expiration;
        this.claims = claims != null ? claims : this.claims;
    }

    public static  JwtFactory withDefaultValues(){
        return JwtFactory.builder().build();
    }


    //jjwt 라이브러리를 사용해 jwt 생성
    public String createToken(JwtProperties jwtProperties) {
        Key key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());

       return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
               .compact();
    }


}
