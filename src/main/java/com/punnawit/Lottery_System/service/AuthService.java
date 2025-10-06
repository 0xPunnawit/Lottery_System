package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Roles;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.repository.UserRepository;
import com.punnawit.Lottery_System.util.UserIdGenerator;
import com.punnawit.Lottery_System.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGenerator userIdGenerator;

    // ================ Start Register ================
    public Users register(UserRegisterRequest request) {
        // ตรวจสอบอีเมลล์ซ้ำ
        if (userRepository.existsByEmail(request.getEmail())) {
            log.error("Email already exists");
            throw new BadRequestException("Email already exists");
        }

        // ตรวจสอบเบอร์โทรศัพท์ซ้ำ
        if (userRepository.existsByPhone(request.getPhone())) {
            log.error("Phone number already exists");
            throw new BadRequestException("Phone number already exists");
        }

        // เข้ารหัสรหัสผ่าน
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Users user = new Users();
        user.setUserId(userIdGenerator.generateUserId());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setRole(Roles.valueOf("USER"));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // บันทึกผู้ใช้ใหม่
        return userRepository.save(user);
    }
    // ================ End Register ================


    // ================ Start Login ================
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    // ================ End Login ================


}
