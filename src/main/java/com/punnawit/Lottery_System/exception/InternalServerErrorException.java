package com.punnawit.Lottery_System.exception;

// InternalServerErrorException (ข้อผิดพลาดภายในเซิร์ฟเวอร์):
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}