package com.my.salty_date.service;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.entity.Comment;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.repository.CommentRepository;
import com.my.salty_date.repository.DatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DatingRepository datingRepository;

    public Long save(CommentDto commentDto) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<Dating> optionalDating = datingRepository.findById(commentDto.getDatingIdx());
        if (optionalDating.isPresent()) {
            Dating dating = optionalDating.get();
            Comment comment = Comment.toSave(commentDto, dating);
            return commentRepository.save(comment).getCommentIdx();
        } else {
            return null;
        }
    }

    public List<CommentDto> findAll(Long datingIdx) {
        Dating dating = datingRepository.findById(datingIdx).get();
        List<Comment> commentList = commentRepository.findAllByDatingOrderByCommentIdxDesc(dating);
        /* EntityList -> DTOList */
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDto commentDto = CommentDto.toCommentDto(comment, datingIdx);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }
}
