package com.punnawit.Lottery_System.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LotteryRequest {

    @NotBlank(message = "Ticket ID cannot be blank")
    @Size(min = 6, max = 6, message = "Ticket ID must be exactly 6 characters")
    private String ticketId;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private int price;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than 0")
    private int amount;
}
