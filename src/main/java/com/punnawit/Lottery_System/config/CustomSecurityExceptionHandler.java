package com.punnawit.Lottery_System.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.punnawit.Lottery_System.dto.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * คลาสนี้จัดการ Error ที่เกิดขึ้นใน Security Filter Chain โดยเฉพาะ
 * - AuthenticationEntryPoint: จัดการ 401 Unauthorized (ยังไม่ Login หรือ Token ไม่ถูกต้อง)
 * - AccessDeniedHandler: จัดการ 403 Forbidden (Login แล้ว แต่ไม่มีสิทธิ์เข้าถึง)
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomSecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("Unauthorized error: {}", authException.getMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Use request.getRequestURI() to get the exact path
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                "UNAUTHORIZED",
                "Authentication is required to access this resource.",
                request.getRequestURI() // Ensure the correct path is sent
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("Access Denied error: {}", accessDeniedException.getMessage());

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // Use request.getRequestURI() to get the exact path
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                "FORBIDDEN",
                "You do not have permission to access this resource.",
                request.getRequestURI() // Ensure the correct path is sent
        );

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
