package com.punnawit.Lottery_System.exception;

// BadRequestException (ข้อมูลไม่ครบ/ไม่ถูกต้อง):
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}