package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.LotteryBusiness;
import com.punnawit.Lottery_System.business.UserTicketBusiness;
import com.punnawit.Lottery_System.dto.request.LotteryPurchaseRequest;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseHistoryResponse;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseResponse;
import com.punnawit.Lottery_System.dto.response.LotteryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotteries")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryBusiness lotteryBusiness;
    private final UserTicketBusiness userTicketBusiness;

    @GetMapping
    public ResponseEntity<LotteryResponse> getAllLotteries() {
        LotteryResponse response = lotteryBusiness.getAllLotteries();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{ticketId}/purchase")
    public ResponseEntity<LotteryPurchaseResponse> purchaseLottery(
            @PathVariable String ticketId,
            @RequestBody LotteryPurchaseRequest request
    ) {
        LotteryPurchaseResponse response = userTicketBusiness.purchaseLottery(ticketId, request);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/purchased")
    public ResponseEntity<List<LotteryPurchaseHistoryResponse>> getPurchasedLotteries() {
        List<LotteryPurchaseHistoryResponse> userTickets = userTicketBusiness.getUserPurchasedLotteries();
        return ResponseEntity.status(HttpStatus.OK).body(userTickets);
    }


}
