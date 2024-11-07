package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.entity.UserEntity;
import com.ucamp.movieusAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserEntity> getUserList(){
        Sort sort = Sort.by(Sort.Direction.ASC, "userNum");
        return userRepository.findAll(sort);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
