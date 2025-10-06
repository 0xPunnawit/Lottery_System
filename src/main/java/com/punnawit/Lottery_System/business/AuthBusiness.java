package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.mapper.UserMapper;
import com.punnawit.Lottery_System.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthBusiness {

    private final AuthService authService;
    private final UserMapper userMapper;

    public String register(UserRegisterRequest request) throws BadRequestException {
        Users user = authService.register(request);

        return "Register Success";
    }

}
