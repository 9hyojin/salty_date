package com.my.salty_date.repository;


import com.my.salty_date.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileRepository extends JpaRepository<File,Long> {

}
