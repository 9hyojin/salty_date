package com.my.salty_date.controller;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;
    @PostMapping("/comment/save")
    public ResponseEntity save(@ModelAttribute CommentDto commentDto) {
        System.out.println("commentDto = " + commentDto);
        Long saveResult = commentService.save(commentDto);
        if (saveResult != null) {
            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getDatingIdx());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

}
