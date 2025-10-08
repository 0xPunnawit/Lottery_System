package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.LotteryBusiness;
import com.punnawit.Lottery_System.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lottery")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryBusiness lotteryBusiness;

    // Get all lotteries
    @GetMapping("/all")
    public ResponseEntity<?> getAllLotteries() {
        return ResponseEntity.ok(lotteryBusiness.getAllLotteries());
    }

    // Get lottery by ticketId
    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getLotteryByTicketId(@PathVariable String ticketId) {
        return ResponseEntity.ok(lotteryBusiness.getLotteryByTicketId(ticketId));
    }

    // Buy lottery
    @PostMapping("/buy/{ticketId}")
    public ResponseEntity<?> buyLottery(
            @PathVariable String ticketId,
            @RequestBody @Valid Users user,
            @RequestParam String paymentDetails
    ) {
        String result = lotteryBusiness.buyLottery(ticketId, user, paymentDetails);
        return ResponseEntity.ok(result);
    }

}
