package com.punnawit.Lottery_System.service;

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

    // Get all lotteries
    public List<Lottery> getAllLotteries() {
        return lotteryRepository.findAll();
    }

    // Get lottery by ticketId
    public Optional<Lottery> getLotteryByTicketId(String ticketId) {
        return lotteryRepository.findById(ticketId);
    }

    // Update lottery amount
    public Lottery updateLotteryAmount(Lottery lottery) {
        return lotteryRepository.save(lottery);
    }

}
