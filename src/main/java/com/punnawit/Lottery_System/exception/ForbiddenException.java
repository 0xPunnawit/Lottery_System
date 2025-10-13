package com.punnawit.Lottery_System.exception;

// ForbiddenException (ไม่มีสิทธิ์เข้าถึง):
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}