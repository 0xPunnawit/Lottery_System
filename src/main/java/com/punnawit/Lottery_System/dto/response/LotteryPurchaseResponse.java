package com.punnawit.Lottery_System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class LotteryPurchaseResponse {
    private String ticketId;
    private int price;
    private int amount;
    private BigDecimal totalPrice;
    private String message;
}