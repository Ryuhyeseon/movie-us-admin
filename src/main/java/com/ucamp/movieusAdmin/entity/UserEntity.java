package com.ucamp.movieusAdmin.entity;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@DynamicUpdate
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;

    @Column(name = "user_email", unique = true, nullable = true)
    private String userEmail;

    @Column(name = "kakao_email", unique = true, nullable = true)
    private String kakaoEmail;

    @Column(name = "user_pw", nullable = true)
    private String userPw;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone", nullable = true)
    private String userPhone;

    @Column(name = "create_dt", nullable = false, updatable = false)
    @CreationTimestamp  // 엔터티가 처음 저장될 때 생성 시간 자동 설정
    private LocalDateTime createDt = LocalDateTime.now();

    @Column(name = "update_dt", nullable = false)
    @UpdateTimestamp  // 엔터티가 수정될 때마다 업데이트 시간 자동 설정
    private LocalDateTime updateDt = LocalDateTime.now();

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<FavoriteList> favoriteLists;

}

