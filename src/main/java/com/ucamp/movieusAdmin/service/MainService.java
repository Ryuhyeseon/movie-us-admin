package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.repository.NoticeRepository;
import com.ucamp.movieusAdmin.repository.ReviewRepository;
import com.ucamp.movieusAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public int countNotice(){
        return noticeRepository.countNoticeId();
    }

    public int countUser(){
        return userRepository.countUserNum();
    }

    public int countReview(){
        return reviewRepository.countReviewId();
    }

}
