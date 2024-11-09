package com.ucamp.movieusAdmin.service;

import com.ucamp.movieusAdmin.entity.UserEntity;
import com.ucamp.movieusAdmin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Long> getMonthlyUserRegistrations() {
        List<Object[]> results = userRepository.findMonthlyUserRegistrationCounts();

        // 결과를 누적 합산 계산을 위한 정렬
        List<Map.Entry<String, Long>> sortedResults = results.stream()
                .map(row -> Map.entry((String) row[0], (Long) row[1]))
                .sorted(Map.Entry.comparingByKey()) // 월별로 정렬
                .collect(Collectors.toList());

        // 누적 합산 계산
        Map<String, Long> cumulativeMap = new LinkedHashMap<>();
        long cumulativeSum = 0;

        for (Map.Entry<String, Long> entry : sortedResults) {
            cumulativeSum += entry.getValue();
            cumulativeMap.put(entry.getKey(), cumulativeSum);
        }

        return cumulativeMap;
    }

}
