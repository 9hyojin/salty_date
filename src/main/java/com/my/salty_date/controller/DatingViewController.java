package com.my.salty_date.controller;

import com.my.salty_date.dto.CommentDto;
import com.my.salty_date.dto.DatingListViewResponse;
import com.my.salty_date.dto.DatingResponse;
import com.my.salty_date.dto.UpdateDatingRequest;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.service.CommentService;
import com.my.salty_date.service.DatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DatingViewController {

    private final DatingService datingService;
    private final CommentService commentService;


    @GetMapping("/")
    public String getDatings(Model model) {
        List<DatingListViewResponse> datings = datingService.findAll().stream()
                .map(DatingListViewResponse::new)
                .toList();
        model.addAttribute("datings", datings);
        return "index";
    }

    @GetMapping("/dating/{datingIdx}")
    public String findById(@PathVariable Long datingIdx, Model model) {
        Dating dating = datingService.findById(datingIdx);
        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
        model.addAttribute("dating", dating);
        model.addAttribute("commentList", commentDtoList);
        return "detailPage";
    }

//    @GetMapping("/")
//    public String findAll(Model model) {
//        List<DatingResponse> datings = datingService.findAll()
//                .stream()
//                .map(DatingResponse::new)
//                .toList();
//        model.addAttribute("datings", datings);
//        return "index";
//
//    }
}


//    @GetMapping("/dating/{datingIdx}")
//    public String findById(@PathVariable Long datingIdx, Model model) {
//        Dating dating = datingService.findById(datingIdx);
//        List<CommentDto> commentDtoList = commentService.findAll(datingIdx);
//        model.addAttribute("dating", dating);
//        model.addAttribute("commentList", commentDtoList);
//        return "detailPage";
//    }
//
//    @PutMapping("/update/{datingIdx}")
//    public String updateForm(@PathVariable Long datingIdx,
//                             @RequestBody UpdateDatingRequest request, Model model) {
//        Dating updatedDating = datingService.update(datingIdx, request);
//        model.addAttribute("dating", updatedDating);
//
//        return "redirect:/" +updatedDating.getDatingIdx();
//
//    }
//
//    @DeleteMapping("/admin/delete/{datingIdx}")
//    public String delete(@PathVariable Long datingIdx) {
//        datingService.delete(datingIdx);
//        return "redirect:/";
//    }
//}
//



