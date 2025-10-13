package com.punnawit.Lottery_System.exception;

// UnauthorizedException (ไม่ได้ล็อกอิน):
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}