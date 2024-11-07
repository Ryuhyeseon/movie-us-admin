package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.entity.AdminEntity;
import com.ucamp.movieusAdmin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password) {
        AdminEntity admin = findByEmail(email);

        if (admin == null) {
            return "emailNotFound";
        }

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            return "passwordIncorrect";
        }

        return "success";
    }

    public AdminEntity findByEmail(String email){
        return adminRepository.findByEmail(email);
    }

    public Boolean checkEmailDuplication(String email) {
        return adminRepository.getByEmail(email).isPresent();
    }

    public AdminEntity register(AdminEntity adminEntity) {
        String encodedPassword = passwordEncoder.encode(adminEntity.getPassword());
        adminEntity.setPassword(encodedPassword);

        return adminRepository.save(adminEntity);
    }

    public void passwordReset(String email, String newPassword) {
        AdminEntity admin = adminRepository.findByEmail(email);
        if (admin != null) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            admin.setPassword(encodedPassword);
        }
    }

    public void update(AdminEntity adminEntity){
        AdminEntity admin = adminRepository.findByEmail(adminEntity.getEmail());

        if (admin != null) {
            // 비밀번호가 변경되었는지 확인하고, 변경되었으면 암호화 처리
            if (adminEntity.getPassword() != null && !adminEntity.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(adminEntity.getPassword());
                admin.setPassword(encodedPassword);  // 암호화된 비밀번호로 설정
            }

            admin.setDept(adminEntity.getDept());
            admin.setName(adminEntity.getName());
            // 변경된 admin을 저장
            adminRepository.save(admin);
        }
    }

}
