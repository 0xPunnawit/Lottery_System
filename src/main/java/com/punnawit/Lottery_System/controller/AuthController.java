package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.AuthBusiness;
import com.punnawit.Lottery_System.dto.request.UserLoginRequest;
import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthBusiness authBusiness;

    // Register
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody UserRegisterRequest request
) throws BadRequestException {
        String register = authBusiness.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Register Success");
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login (
            @Valid @RequestBody UserLoginRequest request
            ) {
        String login = authBusiness.login(request);
        return ResponseEntity.ok(login);
    }



}
