package com.my.salty_date.dto;


import com.my.salty_date.entity.BaseEntity;
import com.my.salty_date.entity.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto extends BaseEntity {
    private Long commentIdx;
    private String commentWriter;
    private String commentContent;
    private Long datingIdx;
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDto(Comment comment, Long datingIdx) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentIdx(comment.getCommentIdx());
        commentDto.setCommentWriter(comment.getCommentWriter());
        commentDto.setCommentContent(comment.getCommentContent());
        commentDto.setCommentCreatedTime(comment.getCreatedTime());
        commentDto.setDatingIdx(datingIdx);
        return commentDto;
    }
}
