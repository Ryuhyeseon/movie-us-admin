package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.dto.ReviewResponseDTO;
import com.ucamp.movieusAdmin.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @GetMapping("/reviewList")
    public String reviewList(Model model) {
        List<ReviewResponseDTO> reviewList = reviewService.getReviewList();

        // ReviewEntity 리스트를 ReviewResponseDTO 리스트로 변환
        List<ReviewResponseDTO> ReviewResponseDTOList = reviewList.stream()
                .map(review -> modelMapper.map(review, ReviewResponseDTO.class))
                .toList();

        model.addAttribute("reviews", ReviewResponseDTOList);
        return "review";
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }

}
