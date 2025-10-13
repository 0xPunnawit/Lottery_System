package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.LotteryBusiness;
import com.punnawit.Lottery_System.dto.request.LotteryRequest;
import com.punnawit.Lottery_System.dto.response.LotteryCreationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LotteryBusiness lotteryBusiness;

    @PostMapping("/lotteries")
    public ResponseEntity<LotteryCreationResponse> addLottery(@Valid @RequestBody LotteryRequest request) {
        lotteryBusiness.addLottery(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LotteryCreationResponse(request.getTicketId()));
    }
}