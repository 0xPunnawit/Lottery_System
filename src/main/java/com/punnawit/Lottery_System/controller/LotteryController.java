package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.LotteryBusiness;
import com.punnawit.Lottery_System.dto.response.LotteryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lotteries")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryBusiness lotteryBusiness;

    @GetMapping
    public ResponseEntity<LotteryResponse> getAllLotteries() {
        LotteryResponse response = lotteryBusiness.getAllLotteries();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
