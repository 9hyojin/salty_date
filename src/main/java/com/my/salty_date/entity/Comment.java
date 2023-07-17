package com.my.salty_date.entity;

import com.my.salty_date.dto.CommentDto;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @Column
    private String commentWriter;

    @Column
    private String commentContent;





    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dating_idx")
    private Dating dating;


    public static Comment toSave(CommentDto commentDto, Dating dating) {
        Comment comment = new Comment();
        comment.setCommentWriter(commentDto.getCommentWriter());
        comment.setCommentContent(commentDto.getCommentContent());
        comment.setDating(dating);
        return comment;
    }

}
