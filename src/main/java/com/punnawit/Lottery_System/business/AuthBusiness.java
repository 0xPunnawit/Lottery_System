package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthBusiness {

    private final AuthService authService;

    public String register(UserRegisterRequest request) {
        authService.register(request);
        return "Register successfully";
    }

}
