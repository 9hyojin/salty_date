package com.my.salty_date.controller;

import com.my.salty_date.dto.DatingResponse;
import com.my.salty_date.service.DatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class   HomeController {

    private final DatingService datingService;



    @GetMapping("/region")
    public String Region(Model model){
        List<DatingResponse> datingList = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("datingList", datingList);
        return "/dating/region";
    }

    @GetMapping("/season")
    public String Season(Model model){
        List<DatingResponse> datingList = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("datingList", datingList);
        return "/dating/season";
    }

    @GetMapping("/in_out")
    public String InOut(Model model){
        List<DatingResponse> datingList = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("datingList", datingList);
        return "/dating/in_out";
    }

    @GetMapping("/age")
    public String Age(Model model){
        List<DatingResponse> datingList = datingService.findAll()
                .stream()
                .map(DatingResponse::new)
                .collect(Collectors.toList());
        model.addAttribute("datingList", datingList);
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