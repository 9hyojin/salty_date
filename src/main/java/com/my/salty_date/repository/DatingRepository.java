package com.my.salty_date.repository;


import com.my.salty_date.entity.Dating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatingRepository extends JpaRepository<Dating,Long> {
    List<Dating> findTopByOrderByDatingIdxDesc();
    List<Dating> findByDatingTitle(String datingTitle);

    List<Dating> findByDatingTitleOrDatingWriter (String datingTitle, String datingWriter);
}
