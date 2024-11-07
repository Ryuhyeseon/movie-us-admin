package com.ucamp.movieusAdmin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {

    private Integer userNum;           // 사용자 번호
    private String userEmail;          // 사용자 이메일
    private String kakaoEmail;         // 카카오 이메일
    private String userName;           // 사용자 이름
    private String userPhone;          // 사용자 전화번호
    private LocalDateTime createDt;    // 생성일
    private LocalDateTime updateDt;    // 수정일
    private String role;               // 사용자 역할

}
