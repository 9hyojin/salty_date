package com.my.salty_date.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_idx", updatable = false)
    private Long tokenIdx;

    @Column(name = "mem_idx", nullable = false, unique = true)
    private Long memIdx;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public RefreshToken(Long memIdx, String refreshToken) {
        this.memIdx = memIdx;
        this.refreshToken = refreshToken;
    }

    public RefreshToken update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;

        return this;
    }
}
