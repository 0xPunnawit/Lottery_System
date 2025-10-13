package com.punnawit.Lottery_System.exception;

// DuplicateDataException (ข้อมูลซ้ำ):
public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException(String message) {
        super(message);
    }
}