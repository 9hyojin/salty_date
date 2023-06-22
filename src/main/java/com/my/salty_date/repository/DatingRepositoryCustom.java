package com.my.salty_date.repository;

import com.my.salty_date.entity.Dating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatingRepositoryCustom {

    List<Dating> findAllWithFiles();

}
