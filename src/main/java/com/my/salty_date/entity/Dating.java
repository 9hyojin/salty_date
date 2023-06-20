package com.my.salty_date.entity;

import com.my.salty_date.constant.DatingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Dating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datingIdx;

//    @Column
//    private String datingWriter;

    @Column
    private String datingTitle;

    @Column
    private String datingAddress;

    @Lob
    @Column
    private String datingContent;


    @Enumerated(EnumType.STRING)
    private DatingStatus datingStatus;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime datingCreatedTime;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime datingUpdatedTime;


    @Builder
    public Dating(String datingTitle, String datingAddress, String datingContent, LocalDateTime datingCreatedTime) {
        this.datingTitle = datingTitle;
        this.datingAddress = datingAddress;
        this.datingContent = datingContent;
        this.datingCreatedTime = datingCreatedTime;
    }


    //게시글 하나당 여러 파일 가능
    @OneToMany(mappedBy = "dating", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<File> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "dating", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();


    public void update(String datingTitle, String datingContent, String datingAddress) {
        this.datingTitle = datingTitle;
        this.datingContent = datingContent;
        this.datingAddress = datingAddress;
    }
}








////        dto -> entity
//    public static Dating toSaveDating(DatingDto datingDto){
//        Dating dating = new Dating();
////        dating.setDatingWriter(datingDto.getDatingWriter());
//        dating.setDatingTitle(datingDto.getDatingTitle());
//        dating.setDatingAddress(datingDto.getDatingAddress());
//        dating.setDatingContent(datingDto.getDatingContent());
//        return dating;
//    }

//    public static Dating toUpdateDating(DatingDto datingDto) {
//        Dating dating = new Dating();
////        dating.setDatingWriter(datingDto.getDatingWriter());
//        dating.setDatingTitle(datingDto.getDatingTitle());
//        dating.setDatingAddress(datingDto.getDatingAddress());
//        dating.setDatingContent(datingDto.getDatingContent());
//        return dating;
//    }

