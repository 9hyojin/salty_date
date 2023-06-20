package com.my.salty_date.dto;


import com.my.salty_date.entity.Dating;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DatingRequest {


    private String datingTitle;

    private String datingAddress;

    private String datingContent;

    private LocalDateTime datingCreatedTime;
    private LocalDateTime datingUpdatedTime;

    private List<MultipartFile> file;

    private List<String> originalFileName;

    private List<String> storedFileName;


    public Dating toDatingEntity() {
        return Dating.builder()
                .datingTitle(datingTitle)
                .datingAddress(datingAddress)
                .datingContent(datingContent)
//                .datingCreatedTime(datingCreatedTime)
                .build();
    }
}
