package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.dto.ReviewResponseDTO;
import com.ucamp.movieusAdmin.entity.Movie;
import com.ucamp.movieusAdmin.entity.ReviewEntity;
import com.ucamp.movieusAdmin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;


    public List<ReviewResponseDTO> getReviewList() {
        Sort sort = Sort.by(Sort.Direction.ASC, "reviewId");
        List<ReviewEntity> reviews = reviewRepository.findAll(sort);
        return reviews.stream()
                .map(this::convertToResponseDTO) // 변환 메서드 호출
                .collect(Collectors.toList());
    }

    public void deleteReview(Long id){
        reviewRepository.deleteById(id);
    }


    private ReviewResponseDTO convertToResponseDTO(ReviewEntity review) {
        Movie movie = review.getMovieId();
        ReviewResponseDTO responseDTO = modelMapper.map(review, ReviewResponseDTO.class);
        if (movie != null) {
            responseDTO.setTmdbId(movie.getTmdbId()); // TMDb ID 추가
            responseDTO.setTitle(movie.getTitle()); // 타이틀 추가
            responseDTO.setPosterPath(movie.getPosterPath()); // 포스터 경로 추가
        }
        return responseDTO;
    }
}
