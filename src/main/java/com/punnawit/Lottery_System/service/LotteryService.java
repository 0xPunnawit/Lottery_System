package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.request.LotteryRequest;
import com.punnawit.Lottery_System.entity.Lottery;
import com.punnawit.Lottery_System.repository.LotteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LotteryService {

    private final LotteryRepository lotteryRepository;

    public void addLottery(LotteryRequest request) {

        Optional<Lottery> byTicketId = lotteryRepository.findByTicketId(request.getTicketId());

        if (byTicketId.isPresent()) {
            // Duplicate lottery numbers can be multiplied.
            Lottery lottery = byTicketId.get();
            lottery.setAmount(lottery.getAmount() + request.getAmount());
            lotteryRepository.save(lottery);
        } else {
            // Create new lottery
            Lottery lottery = new Lottery();
            lottery.setTicketId(request.getTicketId());
            lottery.setPrice(request.getPrice());
            lottery.setAmount(request.getAmount());
            lotteryRepository.save(lottery);
        }
    }

    public List<Lottery> getAllLotteries() {
        return lotteryRepository.findAll();
    }


}
