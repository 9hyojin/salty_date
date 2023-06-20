package com.my.salty_date.controller;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.dto.DatingRequest;
import com.my.salty_date.dto.DatingResponse;
import com.my.salty_date.dto.UpdateDatingRequest;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.service.CommentService;
import com.my.salty_date.service.DatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DatingController {

    private final DatingService datingService;

    private final CommentService commentService;


    @GetMapping("/api/saveDating")
    public String saveDating() {
        return "saveDating";
    }


    @PostMapping("/api/saveDating")
    public ResponseEntity<Dating> save(@RequestBody DatingRequest request) throws IOException {
        Dating savedDating = datingService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDating);

    }


    @GetMapping("/api/dating")
    public ResponseEntity<List<DatingResponse>> findAll() {
        List<DatingResponse> datings = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .toList();
        return ResponseEntity.ok().body(datings);

    }


    @GetMapping("/api/dating/{datingIdx}")
    public ResponseEntity<DatingResponse> findById(@PathVariable Long datingIdx) {
        Dating dating = datingService.findById(datingIdx);
//        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
        return ResponseEntity.ok().body(new DatingResponse(dating));
    }


    @PutMapping("/api/dating/{datingIdx}")
    public ResponseEntity<Dating> updateForm(@PathVariable Long datingIdx,
                                             @RequestBody UpdateDatingRequest request) {
        Dating updatedDating = datingService.update(datingIdx, request);

        return ResponseEntity.ok().body(updatedDating);

    }

    @DeleteMapping("/api/dating/{datingIdx}")
    public ResponseEntity<Void> delete(@PathVariable Long datingIdx) {
        datingService.delete(datingIdx);
        return ResponseEntity.ok().build();
    }
}










//    @PostMapping("/save/datingForm")
//    public String save(@ModelAttribute DatingDto datingDto) throws IOException{
//        datingService.save(datingDto);
//        return "redirect:/"; //작성해서 넘긴 데이터 찾아오기.
//    }


//    @GetMapping("/")
//    public String findAll(Model model) {
//        //DB에서 전체 글의 데이터를 가져와서 index.html에 전달.
//        List<DatingDto> datingDtoList = datingService.findAll();
//        model.addAttribute("datingList", datingDtoList);
//
//        return "index";
//    }



//    @GetMapping("/dating/{datingIdx}")
//    public String findById(@PathVariable Long datingIdx, Model model) {
//        DatingDto datingDto = datingService.findById(datingIdx);
//        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
//        model.addAttribute("dating", datingDto);
//        model.addAttribute("commentList", commentDtoList);
//        return "detailPage";
//    }



//    @GetMapping("/update/{datingIdx}")
//    public String updateForm(@PathVariable Long datingIdx, Model model) {
//        DatingDto datingDto = datingService.findById(datingIdx);
//        model.addAttribute("updateDating", datingDto);
//        return "update";
//    }
//
//    @PostMapping("/update")
//    public String update(@ModelAttribute DatingDto datingDto, Model model) {
//        DatingDto dating = datingService.update(datingDto);
//        model.addAttribute("dating", dating);
//        return "redirect:/" + datingDto.getDatingIdx();
//    }
//


//    @GetMapping("/admin/delete/{datingIdx}")
//    public String delete(@PathVariable Long datingIdx) {
//        datingService.delete(datingIdx);
//        return "redirect:/";
//    }

