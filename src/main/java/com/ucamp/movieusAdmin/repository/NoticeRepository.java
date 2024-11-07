package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

    @Query(value = "SELECT COUNT(notice_id) FROM notice", nativeQuery = true)
    int countNoticeId();

}
