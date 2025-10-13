package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.AuthBusiness;
import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthBusiness authBusiness;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequest request) {
        String register = authBusiness.register(request);

        return ResponseEntity.ok(register);
    }


}
