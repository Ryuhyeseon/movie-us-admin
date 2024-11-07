package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "SELECT COUNT(review_id) FROM review", nativeQuery = true)
    int countReviewId();

}
