//package com.my.salty_date.service;
//
//import com.my.salty_date.dto.CommentDto;
//import com.my.salty_date.entity.Comment;
//import com.my.salty_date.entity.Dating;
//import com.my.salty_date.entity.Member;
//import com.my.salty_date.repository.CommentRepository;
//import com.my.salty_date.repository.DatingRepository;
//import com.my.salty_date.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class CommentService {
//
//    private final CommentRepository commentRepository;
//    private final DatingRepository datingRepository;
//    private final MemberRepository memberRepository;
//
//
////    @Transactional
////    public Long save(CommentDto commentDto,Long dateIdx, User user){
////        Optional<Dating> optionalDating = datingRepository.findById(commentDto.getDatingIdx());
////        if (optionalDating.isPresent()) {
////            Dating dating = optionalDating.get();
////            Comment comment = Comment.builder().build();
////            return commentRepository.save(comment).getCommentIdx();
////        } else {
////            return null;
////        }
////    }
//
//    public Long save(CommentDto commentDto) {
//        /* 부모엔티티(BoardEntity) 조회 */
//        Optional<Dating> optionalDating = datingRepository.findById(commentDto.getDatingIdx());
//        Optional<Member> optionalMember = memberRepository.findById(commentDto.getMemIdx());
//        if (optionalDating.isPresent()) {
//            Dating dating = optionalDating.get();
//            Member member = optionalMember.get();
//            Comment comment = Comment.builder()
//                    .dating(dating)
//                    .commentContent(commentDto.getCommentContent())
//                    .member(member)
//                    .build();
//            return commentRepository.save(comment).getCommentIdx();
//        } else {
//            return null;
//        }
//    }
//
//    public List<CommentDto> findAll(Long datingIdx) {
//        Dating dating = datingRepository.findById(datingIdx).get();
//        List<Comment> commentList = commentRepository.findAllByDatingOrderByCommentIdxDesc(dating);
//        /* EntityList -> DTOList */
//        List<CommentDto> commentDtoList = new ArrayList<>();
//        for (Comment comment : commentList) {
//            CommentDto commentDto = CommentDto.builder()
//                    .commentContent(comment.getCommentContent())
//                    .dateIdx(comment.getDating().getDatingIdx())
//                    .memIdx(comment.getMember().getMemIdx())
//                    .build();
//            commentDtoList.add(commentDto);
//        }
//        return commentDtoList;
//    }
//
//}
//
//
//
//
//
//
//
////    public Long save(CommentDto commentDto) {
////        /* 부모엔티티(BoardEntity) 조회 */
////        Optional<Dating> optionalDating = datingRepository.findById(commentDto.getDatingIdx());
////        if (optionalDating.isPresent()) {
////            Dating dating = optionalDating.get();
////            Comment comment = Comment.toSave(commentDto, dating);
////            return commentRepository.save(comment).getCommentIdx();
////        } else {
////            return null;
////        }
////    }
////
////    public List<CommentDto> findAll(Long datingIdx) {
////        Dating dating = datingRepository.findById(datingIdx).get();
////        List<Comment> commentList = commentRepository.findAllByDatingOrderByCommentIdxDesc(dating);
////        /* EntityList -> DTOList */
////        List<CommentDto> commentDtoList = new ArrayList<>();
////        for (Comment comment : commentList) {
////            CommentDto commentDto = CommentDto.toCommentDto(comment, datingIdx);
////            commentDtoList.add(commentDto);
////        }
////        return commentDtoList;
////    }