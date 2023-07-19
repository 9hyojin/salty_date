package com.my.salty_date.controller;

import com.my.salty_date.dto.*;
import com.my.salty_date.entity.Dating;
//import com.my.salty_date.service.CommentService;
import com.my.salty_date.service.DatingService;
import com.my.salty_date.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class DatingController {

    private final DatingService datingService;
//    private final CommentService commentService;
    private final PaginationService paginationService;


    @GetMapping("/")
    public String findAll(Model model) {
        List<DatingResponse> datingList = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("datingList", datingList);
        return "index";
    }


    @GetMapping("/save/dating")
    public String saveDating() {
        return "saveDating";
    }

    @PostMapping("/save/dating")
    public String save(@ModelAttribute DatingRequest request) throws IOException {
        datingService.save(request);
        return "redirect:/";
    }

    @GetMapping("/dating/{datingIdx}")
    public String findById(@PathVariable Long datingIdx, Model model) {
        Dating dating = datingService.findById(datingIdx);
//        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
        model.addAttribute("dating", new DatingResponse(dating));
//        model.addAttribute("commentList", commentDtoList);
        return "detailPage";
    }

    @PutMapping("/dating/{datingIdx}")
    public String updateForm(@PathVariable Long datingIdx, @ModelAttribute UpdateDatingRequest request, Model model) {
        Dating updatedDating = datingService.update(datingIdx, request);
        model.addAttribute("updatedDating",updatedDating);
        return "redirect:/" + datingIdx;
    }

    @GetMapping("/delete/{datingIdx}")
    public String delete(@PathVariable Long datingIdx) {
        datingService.delete(datingIdx);
        return "redirect:/";
    }



}


