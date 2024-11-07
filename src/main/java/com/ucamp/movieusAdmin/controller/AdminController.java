package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.dto.AdminDTO;
import com.ucamp.movieusAdmin.dto.AdminUpdateDTO;
import com.ucamp.movieusAdmin.dto.PasswordDTO;
import com.ucamp.movieusAdmin.entity.AdminEntity;
import com.ucamp.movieusAdmin.service.AdminService;
import com.ucamp.movieusAdmin.service.MailService;
import com.ucamp.movieusAdmin.service.MainService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final MailService mailService;
    private final MainService mainService;
    private final ModelMapper modelMapper;

    @GetMapping("/loginForm")
    public String loginForm(@ModelAttribute AdminDTO adminDTO) {
        return "login";
    }

    @GetMapping("/login")
    public ResponseEntity<Void> login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {

        String result = adminService.login(email, password);

        if (result.equals("emailNotFound")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (result.equals("passwordIncorrect")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AdminEntity admin = adminService.findByEmail(email);

        if (admin != null) {
            session.setAttribute("admin", admin);
            System.out.println("Admin logged in: " + admin.getName());
        } else {
            System.out.println("Admin not found for email: " + email);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mainPage")
    public String mainPage(Model model) {
        model.addAttribute("countNotice", mainService.countNotice());
        model.addAttribute("countUser", mainService.countUser());
        model.addAttribute("countReview", mainService.countReview());

        return "main-page";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/registerForm")
    public String registerForm(@ModelAttribute AdminDTO adminDTO) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute AdminDTO adminDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        AdminEntity adminEntity = modelMapper.map(adminDTO, AdminEntity.class);
        adminService.register(adminEntity);
        return "login";
    }

    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmailDuplication(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        boolean isDuplicated = adminService.checkEmailDuplication(email);
        response.put("isDuplicated", isDuplicated);
        return response;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgot-password";
    }

    @ResponseBody
    @GetMapping("/sendPasswordResetEmail")
    public String sendPasswordResetEmail(@RequestParam String email) {
        String resetUrl = "http://localhost:8085/admin/passwordResetForm?email=" + email;
        try {
            mailService.sendPasswordResetMail(email, resetUrl);
            return "이메일을 발송하였습니다.";
        } catch (Exception e) {
            e.printStackTrace();
            return "이메일 발송에 실패하였습니다.";
        }
    }

    @GetMapping("/passwordResetForm")
    public String passwordResetForm(@RequestParam String email,
                                    Model model,
                                    @ModelAttribute("newPassword") PasswordDTO newPassword) {
        System.out.println("Received email: " + email);
        model.addAttribute("email", email);
        return "password-reset";
    }

    @PostMapping("/passwordReset")
    public String passwordReset(@RequestParam String email,
                                Model model,
                                @Valid @ModelAttribute("newPassword") PasswordDTO newPassword,
                                BindingResult result) {
        System.out.println("Resetting password for email: " + email);

        if (result.hasErrors()) {
            model.addAttribute("email", email);
            return "password-reset";
        }
        adminService.passwordReset(email, newPassword.getPassword());
        return "login";
    }

    @GetMapping("/updateForm")
    public String updateForm(HttpSession session, Model model) {
        AdminEntity admin = (AdminEntity) session.getAttribute("admin");
        AdminUpdateDTO adminUpdateDTO = modelMapper.map(admin, AdminUpdateDTO.class);
        model.addAttribute("admin", adminUpdateDTO);
        return "update";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("admin") AdminUpdateDTO adminUpdateDTO,
                         BindingResult result,
                         HttpSession session) {
        if (result.hasErrors()) {
            System.out.println("유효성 검사 오류가 발생했습니다.");
            result.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField() + " - Error: " + error.getDefaultMessage());
            });
            return "update";
        }

        AdminEntity currentAdmin = (AdminEntity) session.getAttribute("admin");
        if (currentAdmin != null) {
            AdminEntity updatedAdmin = modelMapper.map(adminUpdateDTO, AdminEntity.class);
            updatedAdmin.setId(currentAdmin.getId());
            adminService.update(updatedAdmin);
            session.setAttribute("admin", updatedAdmin);
        }

        return "redirect:/admin/mainPage";
    }
}