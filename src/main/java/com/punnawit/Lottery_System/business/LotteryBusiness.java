package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.LotteryRequest;
import com.punnawit.Lottery_System.dto.response.LotteryResponse;
import com.punnawit.Lottery_System.entity.Lottery;
import com.punnawit.Lottery_System.service.LotteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LotteryBusiness {

    private final LotteryService lotteryService;

    public void addLottery(LotteryRequest request) {
        lotteryService.addLottery(request);
    }

    public LotteryResponse getAllLotteries() {
        List<Lottery> allLotteries = lotteryService.getAllLotteries();
        return new LotteryResponse(allLotteries);
    }
}
