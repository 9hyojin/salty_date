package com.my.salty_date.dto;

import com.my.salty_date.entity.BaseEntity;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DatingResponse extends BaseEntity{

    private final Long datingIdx;
    private final String datingTitle;
    private final String datingAddress;
    private final String datingContent;
    private final LocalDateTime createdTime;
    private final LocalDateTime updatedTime;

    private final List<File> file;

    private final List<String> originalFileName;

    private final List<String> storedFileName;

    public DatingResponse(Dating dating) {
        this.datingIdx = dating.getDatingIdx();
        this.datingTitle = dating.getDatingTitle();
        this.datingAddress = dating.getDatingAddress();
        this.datingContent = dating.getDatingContent();
        this.createdTime = dating.getCreatedTime();
        this.updatedTime = dating.getUpdatedTime();
        this.file = dating.getFileList();
        List<String> orginalFileNameList = new ArrayList<>();
        List<String> storedFileNameList = new ArrayList<>();
        for (File file : dating.getFileList()) {
            orginalFileNameList.add(file.getOriginalFileName());
            storedFileNameList.add(file.getStoredFileName());
        }
        this.originalFileName = orginalFileNameList;
        this.storedFileName = storedFileNameList;
    }
}
