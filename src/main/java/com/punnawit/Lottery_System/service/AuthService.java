package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Roles;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.DuplicateDataException;
import com.punnawit.Lottery_System.repository.UserRepository;
import com.punnawit.Lottery_System.util.UserIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGenerator userIdGenerator;

    // ================ Start Register ================
    public Users register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateDataException("Email is already in use.");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateDataException("Phone number is already in use.");
        }
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
