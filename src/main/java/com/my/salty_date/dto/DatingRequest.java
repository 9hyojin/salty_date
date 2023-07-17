package com.my.salty_date.dto;


import com.my.salty_date.entity.BaseEntity;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DatingRequest {


    private String datingTitle;

    private String datingAddress;

    private String datingContent;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private List<MultipartFile> file;

    private List<String> originalFileName;

    private List<String> storedFileName;


    public Dating toDatingEntity() {
        return Dating.builder()
                .datingTitle(datingTitle)
                .datingAddress(datingAddress)
                .datingContent(datingContent)
                .createdTime(createdTime)
                .build();
    }






}