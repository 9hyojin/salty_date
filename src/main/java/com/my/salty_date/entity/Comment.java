//package com.my.salty_date.entity;
//
//import com.my.salty_date.dto.CommentDto;
//import javax.persistence.*;
//
//import com.my.salty_date.dto.MemberRequest;
//import lombok.*;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//public class Comment extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long commentIdx;
//
//    @Column
//    private String commentContent;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_idx")
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dating_idx")
//    private Dating dating;
//
//    @Builder
//    public Comment(String commentContent, Member member, Dating dating){
//        this.commentContent = commentContent;
//        this.member = member;
//        this.dating = dating;
//    }
//
//    public void setMember(Member member){
//        this.member = member;
//        if (!member.getComments().contains(this))
//            member.getComments().add(this);
//    }
//
//
//
//}
//
//
//
//
//
//
////    public static Comment toSave(CommentDto commentDto, Dating dating) {
////        Comment comment = new Comment();
////        comment.setCommentWriter(commentDto.getCommentWriter());
////        comment.setCommentContent(commentDto.getCommentContent());
////        comment.setDating(dating);
////        return comment;
////    }