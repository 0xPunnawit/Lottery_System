package com.punnawit.Lottery_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception นี้จะถูกโยน (throw) เมื่อมีการค้นหาข้อมูล (Resource)
 * แต่ไม่พบข้อมูลนั้นในระบบ เช่น ค้นหา user ด้วย id ที่ไม่มีอยู่จริง
 *
 * @ResponseStatus(HttpStatus.NOT_FOUND) จะบอกให้ Spring รู้ว่า
 * ถ้า Exception นี้หลุดออกไปถึง client โดยที่ไม่มี @ExceptionHandler ดักจับ
 * ให้ส่ง HTTP Status 404 Not Found กลับไปโดยอัตโนมัติ
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
