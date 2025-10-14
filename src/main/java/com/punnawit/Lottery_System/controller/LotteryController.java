package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.LotteryBusiness;
import com.punnawit.Lottery_System.business.UserTicketBusiness;
import com.punnawit.Lottery_System.dto.request.CancelLotteryRequest;
import com.punnawit.Lottery_System.dto.request.LotteryPurchaseRequest;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseHistoryResponse;
import com.punnawit.Lottery_System.dto.response.LotteryPurchaseResponse;
import com.punnawit.Lottery_System.dto.response.LotteryResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    @Operation(summary = "Get all lotteries", description = "Fetch all available lottery data")
    public ResponseEntity<LotteryResponse> getAllLotteries() {
        LotteryResponse response = lotteryBusiness.getAllLotteries();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{ticketId}/purchase")
    @Operation(summary = "Purchase lottery", description = "Purchase a specific lottery ticket")
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

    // DELETE endpoint to cancel the lottery purchase and return the tickets to the lottery stock
    @DeleteMapping("/{ticketId}/purchase")
    @Operation(summary = "Cancel purchase and return lottery", description = "Cancel the purchased lottery and return the tickets to the lottery stock.")
    public ResponseEntity<String> cancelLotteryPurchase(
            @PathVariable String ticketId,
            @Valid @RequestBody CancelLotteryRequest request
    ) {
        String response = userTicketBusiness.cancelLotteryPurchase(ticketId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
