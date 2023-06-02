package com.my.salty_date.dto;

import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DatingDto {

    private Long datingIdx;

    private String datingWriter;

    private String datingTitle;

    private String datingAddress;

    private LocalDateTime datingCreatedTime;
    private LocalDateTime datingUpdatedTime;
    private String datingContent;
    private String datingPassword;


    private List<MultipartFile> file;


//    private MultipartFile file;

    private List<String> originalFileName;

    private List<String> storedFileName;

//    private List<String> mainImg;


    public DatingDto(Long datingIdx, String datingWriter ,String datingTitle, LocalDateTime datingCreatedTime) {
        this.datingIdx = datingIdx;
        this.datingWriter = datingWriter;
        this.datingTitle = datingTitle;
//        this.boardHits = boardHits;
        this.datingCreatedTime = datingCreatedTime;
    }



    //entity -> dto
    public static DatingDto toDatingDto (Dating dating){
        DatingDto datingDto = new DatingDto();
        datingDto.setDatingIdx(dating.getDatingIdx());
        datingDto.setDatingWriter(dating.getDatingWriter());
        datingDto.setDatingTitle(dating.getDatingTitle());
        datingDto.setDatingAddress(dating.getDatingAddress());
        datingDto.setDatingContent(dating.getDatingContent());
        datingDto.setDatingPassword(dating.getDatingPassword());
        datingDto.setDatingCreatedTime(dating.getCreatedTime());
        datingDto.setDatingUpdatedTime(dating.getUpdatedTime());

        // 파일 이름을 가져가야 함.
        // orginalFileName, storedFileName : board_file_table(BoardFileEntity)
        // join
        // select * from board_table b, board_file_table bf where b.id=bf.board_id
        // and where b.id=?
        List<String>orginalFileNameList = new ArrayList<>();
        List<String>storedFileNameList = new ArrayList<>();
//        List<String>mainImgList = new ArrayList<>();
        for(File file : dating.getFileList()){
            orginalFileNameList.add(file.getOriginalFileName());
            storedFileNameList.add(file.getStoredFileName());
//            mainImgList.add(fileEntity.getMainImg());
        }
        datingDto.setOriginalFileName(orginalFileNameList);
        datingDto.setStoredFileName(storedFileNameList);
//        dateDto.setMainImg(mainImgList);
        return  datingDto;
    }


}
