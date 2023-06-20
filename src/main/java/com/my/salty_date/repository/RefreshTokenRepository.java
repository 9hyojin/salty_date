package com.my.salty_date.repository;

import com.my.salty_date.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
     Optional<RefreshToken> findByMemIdx(Long memIdx);
     Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
