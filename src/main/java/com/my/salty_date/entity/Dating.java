package com.my.salty_date.entity;

import com.my.salty_date.constant.DatingStatus;
import com.my.salty_date.dto.DatingDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Dating extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datingIdx;

    @Column
    private String datingWriter;

    @Column
    private String datingTitle;

    @Column
    private String datingAddress;

    @Lob
    @Column
    private String datingContent;

    @Column
    private String datingPassword;

    @Enumerated(EnumType.STRING)
    private DatingStatus datingStatus;



    //게시글 하나당 여러 파일 가능
    @OneToMany(mappedBy = "dating",cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<File>fileList = new ArrayList<>();

    @OneToMany(mappedBy = "dating", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    //dto -> entity
    public static Dating toSaveDating(DatingDto datingDto){
        Dating dating = new Dating();
        dating.setDatingWriter(datingDto.getDatingWriter());
        dating.setDatingTitle(datingDto.getDatingTitle());
        dating.setDatingAddress(datingDto.getDatingAddress());
        dating.setDatingContent(datingDto.getDatingContent());
        dating.setDatingPassword(datingDto.getDatingPassword());
        return dating;
    }

    public static Dating toUpdateDating(DatingDto datingDto) {
        Dating dating = new Dating();
        dating.setDatingWriter(datingDto.getDatingWriter());
        dating.setDatingTitle(datingDto.getDatingTitle());
        dating.setDatingAddress(datingDto.getDatingAddress());
        dating.setDatingContent(datingDto.getDatingContent());
        dating.setDatingPassword(datingDto.getDatingPassword());
        return dating;
    }




}
