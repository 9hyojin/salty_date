package com.my.salty_date.controller;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.dto.DatingRequest;
import com.my.salty_date.dto.DatingResponse;
import com.my.salty_date.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;
    @PostMapping("/save/comment")
    public ResponseEntity save(@ModelAttribute CommentDto commentDto) {
        Long saveResult = commentService.save(commentDto);
        if (saveResult != null) {
            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getDatingIdx());
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
}

