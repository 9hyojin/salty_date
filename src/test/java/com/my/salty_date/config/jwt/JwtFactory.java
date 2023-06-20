package com.my.salty_date.config.jwt;


import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static java.util.Collections.emptyMap;

@Getter
public class JwtFactory {
    private String subject = "test@email.com";
    private Date issueAt = new Date();
    private Date expiration = new Date(new Date().getTime()+ Duration.ofDays(14).toMillis());
    private Map<String, Object> claims = emptyMap();



    @Builder
    //빌더패턴을 사용해서 설정이 필요한 데이터만 선택설정
    public JwtFactory(String subject, Date issueAt, Date expiration, Map<String,Object>claims){
        this.subject = subject != null ? subject : this.subject;
        this.issueAt = issueAt != null ? issueAt : this.issueAt;
        this.expiration = expiration != null ? expiration : this.expiration;
        this.claims = claims != null ? claims : this.claims;
    }

    public static JwtFactoryBuilder builder() {
        return new JwtFactoryBuilder();
    }

    public static JwtFactory withDefaultValues(){
        return JwtFactory.builder().build();
    }


    //jjwt 라이브러리를 사용해서 JWT 생성
    public String createToken(JwtProperties jwtProperties){

        return Jwts.builder()
                .setSubject(subject)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issueAt)
                .setExpiration(expiration)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,jwtProperties.getSecretKey())
                .compact();
    }

    public static class JwtFactoryBuilder {
        private String subject;
        private Date issueAt;
        private Date expiration;
        private Map<String, Object> claims;

        public JwtFactoryBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public JwtFactoryBuilder issueAt(Date issueAt) {
            this.issueAt = issueAt;
            return this;
        }

        public JwtFactoryBuilder expiration(Date expiration) {
            this.expiration = expiration;
            return this;
        }

        public JwtFactoryBuilder claims(Map<String, Object> claims) {
            this.claims = claims;
            return this;
        }

        public JwtFactory build() {
            return new JwtFactory(subject, issueAt, expiration, claims);
        }
    }
}
