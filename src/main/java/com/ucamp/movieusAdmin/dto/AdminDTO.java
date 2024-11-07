package com.ucamp.movieusAdmin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminDTO {

    private Long id;

    @NotEmpty(message = "dept는 필수 입력항목입니다.")
    private String dept;

    @NotEmpty(message = "name은 필수 입력항목입니다.")
    private String name;

    @NotEmpty(message = "email은 필수 입력항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotEmpty(message = "password는 필수 입력항목입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "비밀번호는 대문자, 소문자, 숫자 및 특수 문자를 포함해야 합니다."
    )
    private String password;

}
