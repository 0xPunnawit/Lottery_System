package com.punnawit.Lottery_System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LotteryPurchaseHistoryResponse {
    private Long userTicketId;
    private String ticketId;
    private int price;
    private int amount;
    private LocalDateTime purchaseDate;
}
