package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "SELECT COUNT(user_num) FROM users", nativeQuery = true)
    int countUserNum();

}
