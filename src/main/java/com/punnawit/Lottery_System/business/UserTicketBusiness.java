package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.LotteryPurchaseRequest;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseHistoryResponse;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseResponse;
import com.punnawit.Lottery_System.service.UserTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTicketBusiness {

    private final UserTicketService userTicketService;

    public LotteryPurchaseResponse purchaseLottery(String ticketId, LotteryPurchaseRequest request) {
        return userTicketService.purchaseLottery(ticketId, request.getAmount());
    }

    public List<LotteryPurchaseHistoryResponse> getUserPurchasedLotteries() {
        return userTicketService.getUserPurchasedLotteries();
    }

    public String cancelLotteryPurchase(String ticketId, LotteryPurchaseRequest request) {
        return userTicketService.cancelLotteryPurchase(ticketId, request.getAmount());
    }

}
