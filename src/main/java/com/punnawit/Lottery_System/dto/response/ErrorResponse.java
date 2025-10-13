package com.punnawit.Lottery_System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String error;
    private String message;
    private int status;
    private long timestamp;
}
