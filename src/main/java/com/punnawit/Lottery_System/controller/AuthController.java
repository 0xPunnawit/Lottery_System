package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.AuthBusiness;
import com.punnawit.Lottery_System.dto.request.UserLoginRequest;
import com.punnawit.Lottery_System.dto.request.UserRegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    // Register
    @Operation(summary = "Register a new user", description = "This endpoint registers a new user into the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterRequest request) {
        String register = authBusiness.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    // Login
    @Operation(summary = "User login", description = "This endpoint authenticates the user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful, JWT token returned"),
            @ApiResponse(responseCode = "401", description = "Invalid login credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginRequest request) {
        String login = authBusiness.login(request);

        return ResponseEntity.ok(login);
    }


}
