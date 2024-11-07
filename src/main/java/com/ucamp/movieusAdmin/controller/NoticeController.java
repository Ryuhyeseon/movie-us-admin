package com.ucamp.movieusAdmin.controller;

import com.ucamp.movieusAdmin.dto.NoticeResponseDTO;
import com.ucamp.movieusAdmin.entity.NoticeEntity;
import com.ucamp.movieusAdmin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final ModelMapper modelMapper;

    @GetMapping("/noticeList")
    public String noticeList(Model model) {
        List<NoticeEntity> noticeList = noticeService.getNoticeList(); // 공지사항 리스트 가져오기

        // NoticeEntity를 NoticeResponseDTO로 변환
        List<NoticeResponseDTO> noticeDTOList = noticeList.stream()
                .map(notice -> modelMapper.map(notice, NoticeResponseDTO.class))
                .toList();

        model.addAttribute("notices", noticeDTOList); // 모델에 추가
        return "notice"; // notice.html로 반환
    }

}
