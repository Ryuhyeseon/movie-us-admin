package com.ucamp.movieusAdmin.dto;

import com.ucamp.movieusAdmin.entity.UserEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ReviewResponseDTO {
    private Long reviewId; // 리뷰 ID
    private UserEntity user; // 사용자
    private Long tmdbId; // 영화 ID
    private String title; // 영화 ID
    private BigDecimal rating; // 평점
    private String comment; // 리뷰 내용
    private LocalDateTime reviewDate; // 리뷰 작성 날짜

    private String reportUserEmail; // 리뷰 신고 user email
    private Boolean report; // 신고 여부
    private String reportComment; // 신고 내용
    private LocalDateTime reviewReportDate; // 리뷰 신고 날짜
}
