package com.ucamp.movieusAdmin.repository;

import com.ucamp.movieusAdmin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    Optional<AdminEntity> getByEmail(String email);
    AdminEntity findByEmail(String email);
}
