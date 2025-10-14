package com.punnawit.Lottery_System.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelLotteryRequest {

    @Min(value = 1, message = "Amount must be greater than zero")
    private int amount;
}
