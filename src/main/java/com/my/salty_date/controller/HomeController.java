package com.my.salty_date.controller;

import com.my.salty_date.dto.DatingResponse;
import com.my.salty_date.service.DatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class   HomeController {

    private final DatingService datingService;



    @GetMapping("/dating/region")
    public String Region(Model model){
        List<DatingResponse> datings = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .toList();
        model.addAttribute("datingList", datings);
        return "/dating/region";
    }

    @GetMapping("/dating/season")
    public String Season(Model model){
        List<DatingResponse> datings = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .toList();
        model.addAttribute("datingList", datings);
        return "/dating/season";
    }

    @GetMapping("/dating/in_out")
    public String InOut(Model model){
        List<DatingResponse> datings = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .toList();
        model.addAttribute("datingList", datings);
        return "/dating/in_out";
    }

    @GetMapping("/dating/age")
    public String Age(Model model){
        List<DatingResponse> datings = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .toList();
        model.addAttribute("datingList", datings);
        return "/dating/age";
    }

    @GetMapping("/admin/page")
    public String admin(){
        return "admin";
    }


}




//    @GetMapping("/share")
//    public String share(Model model){
//        List<DatingDto> datingDtoList = datingService.findAll();
//        model.addAttribute("datingList", datingDtoList);
//        return "saveDating";
//    }