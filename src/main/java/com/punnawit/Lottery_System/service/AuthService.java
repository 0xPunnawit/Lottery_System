package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Roles;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.repository.UserRepository;
import com.punnawit.Lottery_System.util.UserIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGenerator userIdGenerator;;

    // Register
    public Users register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            // Throw
            System.out.println("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            // Throw
            System.out.println("Phone already exists");
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

}
