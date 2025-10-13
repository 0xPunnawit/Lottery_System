package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.UserLoginRequest;
import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.UnauthorizedException;
import com.punnawit.Lottery_System.service.AuthService;
import com.punnawit.Lottery_System.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthBusiness {

    private final AuthService authService;
    private final TokenUtil tokenUtil;

    public String register(UserRegisterRequest request) {
        authService.register(request);
        return "Register successfully";
    }

    public String login(UserLoginRequest request) {

        Optional<Users> byEmail = authService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw new UnauthorizedException("Invalid login credentials.");
        }

        Users users = byEmail.get();
        if (!authService.matchPassword(request.getPassword(), users.getPassword())) {
            throw new UnauthorizedException("Invalid login credentials.");
        }

        return tokenUtil.tokenize(users);
    }

}
