package com.my.salty_date.entity;

import com.my.salty_date.constant.DatingStatus;
import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Dating extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long datingIdx;


    @Column
    private String datingTitle;

    @Column
    private String datingAddress;

    @Lob
    @Column
    private String datingContent;


    @Enumerated(EnumType.STRING)
    private DatingStatus datingStatus;



    @Builder
    public Dating(String datingTitle, String datingAddress, String datingContent, LocalDateTime createdTime) {
        this.datingTitle = datingTitle;
        this.datingAddress = datingAddress;
        this.datingContent = datingContent;
        this.createdTime = createdTime;

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







