package com.ucamp.movieusAdmin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminUpdateDTO {

    private Long id;

    @NotEmpty(message = "dept는 필수 입력항목입니다.")
    private String dept;

    @NotEmpty(message = "name은 필수 입력항목입니다.")
    private String name;

    @NotEmpty(message = "email은 필수 입력항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

}
