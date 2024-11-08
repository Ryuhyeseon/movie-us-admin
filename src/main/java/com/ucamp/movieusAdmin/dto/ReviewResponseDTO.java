package com.ucamp.movieusAdmin.dto;

import com.ucamp.movieusAdmin.entity.UserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long reviewId; // 리뷰 ID
    private UserEntity user; // 사용자
    private Long movieId; // 영화 ID
    private Long tmdbId; // 영화 ID
    private String title; // 영화 ID
    private String posterPath; // 포스터 경로
    private BigDecimal rating; // 평점
    private String comment; // 리뷰 내용
    private LocalDateTime reviewDate; // 리뷰 작성 날짜
}
