package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.dto.UserResponseDTO;
import com.ucamp.movieusAdmin.entity.UserEntity;
import com.ucamp.movieusAdmin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/userList")
    public String userList(Model model) {
        List<UserEntity> userList = userService.getUserList();

        // UserEntity 리스트를 UserResponseDTO 리스트로 변환
        List<UserResponseDTO> UserResponseDTOList = userList.stream()
                .map(user -> modelMapper.map(userList, UserResponseDTO.class))
                .toList();

        model.addAttribute("users", userList); // 모델에 추가
        return "user"; // user.html로 반환
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
