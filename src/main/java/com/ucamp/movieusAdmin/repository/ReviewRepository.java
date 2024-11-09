package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.Movie;
import com.ucamp.movieusAdmin.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "SELECT COUNT(review_id) FROM review", nativeQuery = true)
    int countReviewId();

}
