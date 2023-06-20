package com.my.salty_date.repository;


import com.my.salty_date.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

//    boolean existsByEmail(String email);
//
//    Member findByRefreshToken(String refreshToken);

}
