//package com.my.salty_date.controller;
//
//import com.my.salty_date.dto.CommentDto;
//import com.my.salty_date.dto.DatingRequest;
//import com.my.salty_date.dto.DatingResponse;
//import com.my.salty_date.entity.Member;
//import com.my.salty_date.service.CommentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.Banner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Controller
//@RequiredArgsConstructor
//public class CommentController {
//    private final CommentService commentService;
//
////    @PostMapping("/save/comment")
////    public Long saveComment(@RequestBody CommentDto commentDto,
////                            @PathVariable Long dateIdx,
////                            @AuthenticationPrincipal User user){
////        return commentService.save(commentDto,dateIdx,user);
////    }
//
//
//    @PostMapping("/save/comment")
//    public ResponseEntity save(Model model,
//                               @ModelAttribute CommentDto commentDto,
//                               @AuthenticationPrincipal Member member) {
//        Long saveResult = commentService.save(commentDto);
//
//        if (saveResult != null) {
//            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getDatingIdx());
//            model.addAttribute("writer",member);
//            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }
//    }
//
////    public String findAll(Model model) {
////        List<DatingResponse> datingList = datingService.findAll()
////                .stream()
////                .map(DatingResponse::new)
////                .collect(Collectors.toList());
////        model.addAttribute("datingList", datingList);
////        return "index";
//
////    public String save(@ModelAttribute DatingRequest request) throws IOException {
////        datingService.save(request);
////        return "redirect:/";
//
//
//}
//
//
