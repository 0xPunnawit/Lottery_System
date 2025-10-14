package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.request.UpdateFullNameRequest;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.ResourceNotFoundException;
import com.punnawit.Lottery_System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users updateFullName(UpdateFullNameRequest request) {
        // ดึง userId จาก SecurityContext (JWT token)
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // ค้นหาผู้ใช้จาก userId
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        // อัพเดตชื่อผู้ใช้
        Users user = userOpt.get();
        user.setFullName(request.getFullName());
        user.setUpdatedAt(LocalDateTime.now());

        // บันทึกการอัพเดต
        return userRepository.save(user);
    }

}
