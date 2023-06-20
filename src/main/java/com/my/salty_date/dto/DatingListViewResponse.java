package com.my.salty_date.dto;

import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DatingListViewResponse {

    private final Long datingIdx;
    private final String datingTitle;
    private final String datingAddress;
    private final String datingContent;
    private final LocalDateTime datingCreatedTime;
    private final List<String> originalFileName;

    private final List<String> storedFileName;


    public DatingListViewResponse(Dating dating){
        this.datingIdx = dating.getDatingIdx();
        this.datingTitle = dating.getDatingTitle();
        this.datingAddress = dating.getDatingAddress();
        this.datingContent = dating.getDatingContent();
        this.datingCreatedTime = dating.getDatingCreatedTime();
        List<String>orginalFileNameList = new ArrayList<>();
        List<String>storedFileNameList = new ArrayList<>();
        for(File file : dating.getFileList()){
            orginalFileNameList.add(file.getOriginalFileName());
            storedFileNameList.add(file.getStoredFileName());
        }
        this.originalFileName = orginalFileNameList;
        this.storedFileName = storedFileNameList;
    }






}
