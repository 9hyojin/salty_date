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

//    @Column
//    private String mainImg;


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












//    public static FileEntity toFileEntity(DateEntity dateEntity, String originalFileName, String storedFileName, String mainImg){
//        FileEntity fileEntity = new FileEntity();
//        fileEntity.setOriginalFileName(originalFileName);
//        fileEntity.setStoredFileName(storedFileName);
//        fileEntity.setDateEntity(dateEntity); //pk값이 아닌 부모엔티티 객체를 넘겨줘야한다.
//        fileEntity.setMainImg(mainImg);
//        return fileEntity;
//    }
//

//    public static File toFile(Dating dating, String originalFileName, String storedFileName){
//        File file = new File();
//        file.setOriginalFileName(originalFileName);
//        file.setStoredFileName(storedFileName);
//        file.setDating(dating);
//        return file;
//    }

