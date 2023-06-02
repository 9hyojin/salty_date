package com.my.salty_date.controller;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.dto.DatingDto;
import com.my.salty_date.service.CommentService;
import com.my.salty_date.service.DatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DatingController {

    private final DatingService datingService;

    private final CommentService commentService;



    @GetMapping("/saveDating")
    public String saveDating(){
        return "saveDating";
    }


    @PostMapping("/dating")
    public String save(@ModelAttribute DatingDto datingDto) throws IOException{
        datingService.save(datingDto);
        return "redirect:/"; //작성해서 넘긴 데이터 찾아오기.
    }

    @GetMapping("/")
    public String findAll(Model model) {
        //DB에서 전체 글의 데이터를 가져와서 index.html에 전달.
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);

        return "index";
    }

    @GetMapping("/dating/{datingIdx}")
    public String findById(@PathVariable Long datingIdx, Model model) {
        DatingDto datingDto = datingService.findById(datingIdx);
        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
        model.addAttribute("dating", datingDto);
        model.addAttribute("commentList", commentDtoList);
        return "detailPage";
    }

    @GetMapping("/update/{datingIdx}")
    public String updateForm(@PathVariable Long datingIdx, Model model) {
        DatingDto datingDto = datingService.findById(datingIdx);
        model.addAttribute("updateDating", datingDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DatingDto datingDto, Model model) {
        DatingDto dating = datingService.update(datingDto);
        model.addAttribute("dating", dating);
        return "redirect:/" + datingDto.getDatingIdx();
    }

    @GetMapping("/delete/{datingIdx}")
    public String delete(@PathVariable Long datingIdx) {
        datingService.delete(datingIdx);
        return "redirect:/";
    }

}
