package com.punnawit.Lottery_System.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "adminuser0";

        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);

    }
}
