package com.my.salty_date.controller;

import com.my.salty_date.dto.DatingDto;
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



    @GetMapping("/region")
    public String Region(Model model){
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);
        return "region";
    }

    @GetMapping("/season")
    public String Season(Model model){
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);
        return "season";
    }

    @GetMapping("/in_out")
    public String InOut(Model model){
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);
        return "in_out";
    }

    @GetMapping("/age")
    public String Age(Model model){
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);
        return "age";
    }

    @GetMapping("/share")
    public String Share(Model model){
        List<DatingDto> datingDtoList = datingService.findAll();
        model.addAttribute("datingList", datingDtoList);
        return "saveDating";
    }


}
