package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.dto.NoticeRequestDTO;
import com.ucamp.movieusAdmin.entity.AdminEntity;
import com.ucamp.movieusAdmin.entity.NoticeEntity;
import com.ucamp.movieusAdmin.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeRestController {

    private final NoticeService noticeService;
    private final ModelMapper modelMapper;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotice(@PathVariable("id") Integer id,
                                          @Valid @RequestBody NoticeRequestDTO updatedNoticeDTO,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        NoticeEntity updatedNotice = modelMapper.map(updatedNoticeDTO, NoticeEntity.class);
        updatedNotice.setNoticeId(id);

        noticeService.updateNotice(updatedNotice);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> registNotice(HttpSession session,
                                          @Valid @RequestBody NoticeRequestDTO noticeRequestDTO,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        AdminEntity admin = (AdminEntity) session.getAttribute("admin");
        NoticeEntity newNotice = modelMapper.map(noticeRequestDTO, NoticeEntity.class);
        newNotice.setAdmin(admin);

        noticeService.addNotice(newNotice);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }
}


