package com.ucamp.movieusAdmin.dto;

import com.ucamp.movieusAdmin.entity.AdminEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeResponseDTO {

    private Integer noticeId;     // 공지사항 ID
    private AdminEntity admin;      // 관리자 ID
    private String title;         // 공지사항 제목
    private String content;       // 공지사항 내용
    private LocalDateTime createdAt;  // 생성 일자
    private LocalDateTime updatedAt;  // 수정 일자

}
