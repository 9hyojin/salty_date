package com.my.salty_date.entity;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;


    //파일 여러개에 데이트폼은 하나만 가능
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dating_idx")
    private Dating dating;


    @Builder
    public File(Dating dating, String originalFileName, String storedFileName) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.dating = dating;
    }
}


