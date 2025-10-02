package com.punnawit.Lottery_System.util;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class UserIdGenerator {

    private static final Random random = new Random();

    public String generateUserId() {
        long number = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }
}
