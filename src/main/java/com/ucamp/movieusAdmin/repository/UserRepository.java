package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT COUNT(user_num) FROM users", nativeQuery = true)
    int countUserNum();

    @Query("SELECT FUNCTION('DATE_FORMAT', u.createDt, '%Y-%m') AS month, COUNT(u) AS count " +
            "FROM UserEntity u " +
            "GROUP BY FUNCTION('DATE_FORMAT', u.createDt, '%Y-%m') " +
            "ORDER BY month ASC")
    List<Object[]> findMonthlyUserRegistrationCounts();
}
