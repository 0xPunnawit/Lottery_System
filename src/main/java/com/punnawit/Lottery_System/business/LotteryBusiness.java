package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.LotteryRequest;
import com.punnawit.Lottery_System.service.LotteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LotteryBusiness {

    private final LotteryService lotteryService;

    public void addLottery(LotteryRequest request) {
        lotteryService.addLottery(request);
    }
}
