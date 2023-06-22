package com.my.salty_date.dto;

import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DatingResponse {

    private final Long datingIdx;
    private final String datingTitle;
    private final String datingAddress;
    private final String datingContent;
    private final LocalDateTime datingCreatedTime;
    private final LocalDateTime datingUpdatedTime;

    private final List<File> file;

    private final List<String> originalFileName;

    private final List<String> storedFileName;

    public DatingResponse(Dating dating) {
        this.datingIdx = dating.getDatingIdx();
        this.datingTitle = dating.getDatingTitle();
        this.datingAddress = dating.getDatingAddress();
        this.datingContent = dating.getDatingContent();
        this.datingCreatedTime = dating.getDatingCreatedTime();
        this.datingUpdatedTime = dating.getDatingUpdatedTime();
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

    //entity -> dto
//    public static DatingDto toDatingDto (Dating dating){
//        DatingDto datingDto = new DatingDto();
//        datingDto.setDatingIdx(dating.getDatingIdx());
////        datingDto.setDatingWriter(dating.getDatingWriter());
//        datingDto.setDatingTitle(dating.getDatingTitle());
//        datingDto.setDatingAddress(dating.getDatingAddress());
//        datingDto.setDatingContent(dating.getDatingContent());
//        datingDto.setDatingCreatedTime(dating.getCreatedTime());
//        datingDto.setDatingUpdatedTime(dating.getUpdatedTime());
//
//        // 파일 이름을 가져가야 함.
//        // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
//        // join
//        // select * from board_table b, board_file_table bf where b.id=bf.board_id
//        // and where b.id=?
//        List<String>orginalFileNameList = new ArrayList<>();
//        List<String>storedFileNameList = new ArrayList<>();
//        for(File file : dating.getFileList()){
//            orginalFileNameList.add(file.getOriginalFileName());
//            storedFileNameList.add(file.getStoredFileName());
//        }
//        datingDto.setOriginalFileName(orginalFileNameList);
//        datingDto.setStoredFileName(storedFileNameList);
//        return  datingDto;
//    }
