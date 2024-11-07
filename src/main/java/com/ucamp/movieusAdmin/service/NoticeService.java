package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.entity.NoticeEntity;
import com.ucamp.movieusAdmin.repository.NoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeEntity> getNoticeList(){
        Sort sort = Sort.by(Sort.Direction.ASC, "noticeId");
        return noticeRepository.findAll(sort);
    }

    public void updateNotice(NoticeEntity updatedNotice) {
        // 공지사항을 ID로 조회
        NoticeEntity existingNotice = noticeRepository.findById(updatedNotice.getNoticeId())
                .orElseThrow(() -> new EntityNotFoundException("Notice not found: " + updatedNotice.getNoticeId()));

        // 기존 공지사항의 필드를 업데이트
        existingNotice.setTitle(updatedNotice.getTitle());      // 제목 업데이트
        existingNotice.setContent(updatedNotice.getContent());  // 내용 업데이트
        existingNotice.setUpdatedAt(LocalDateTime.now());       // 수정 일자 업데이트
    }

    public void addNotice(NoticeEntity updatedNotice){
        noticeRepository.save(updatedNotice);
    }

    public void deleteNotice(Integer id) {
        noticeRepository.deleteById(id);
    }

}
