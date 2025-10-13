package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.LotteryPurchaseRequest;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseResponse;
import com.punnawit.Lottery_System.service.UserTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTicketBusiness {

    private final UserTicketService userTicketService;

    public LotteryPurchaseResponse purchaseLottery(String ticketId, LotteryPurchaseRequest request) {
        return userTicketService.purchaseLottery(ticketId, request.getAmount());
    }
}
