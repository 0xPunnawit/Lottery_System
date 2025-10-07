package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.UserLoginRequest;
import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.LoginException;
import com.punnawit.Lottery_System.mapper.UserMapper;
import com.punnawit.Lottery_System.service.AuthService;
import com.punnawit.Lottery_System.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusiness {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final TokenUtil tokenUtil;

    // Register
    public String register(UserRegisterRequest request) throws BadRequestException {
        Users user = authService.register(request);

        return "Register Success";
    }

    // Login
    public String login(UserLoginRequest request) {

        Optional<Users> byEmail = authService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            log.error("Email not found");
            throw new LoginException("Invalid email or password");
        }

        Users users = byEmail.get();
        if (!authService.matchPassword(request.getPassword(), users.getPassword())) {
            log.error("Password not match");
            throw new LoginException("Invalid email or password");
        }
        String jwtToken = tokenUtil.generateToken(users);

        return jwtToken;
    }
}
