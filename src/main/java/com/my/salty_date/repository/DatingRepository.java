package com.my.salty_date.repository;


import com.my.salty_date.entity.Dating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatingRepository extends JpaRepository<Dating,Long> ,DatingRepositoryCustom {
    List<Dating> findByDatingIdx(Long dateIdx);
    List<Dating> findByDatingTitle(String datingTitle);
}
