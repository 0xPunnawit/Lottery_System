package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.entity.Lottery;
import com.punnawit.Lottery_System.entity.UserTicket;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.BadRequestException;
import com.punnawit.Lottery_System.repository.UserTicketRepository;
import com.punnawit.Lottery_System.service.LotteryService;
import com.punnawit.Lottery_System.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LotteryBusiness {

    private final LotteryService lotteryService;
    private final UserTicketRepository userTicketRepository;

    // Get All lotteries
    public List<Lottery> getAllLotteries() {
        return lotteryService.getAllLotteries();
    }

    // Get lottery by ticketId
    public Lottery getLotteryByTicketId(String ticketId) {
        return lotteryService.getLotteryByTicketId(ticketId)
                .orElseThrow(() -> new BadRequestException("Lottery not found"));
    }

    // Buy lottery
    public String buyLottery(String ticketId, Users user, String paymentDetails) {

        Lottery lottery = getLotteryByTicketId(ticketId);
        if (lottery.getAmount() <= 0) {
            throw new BadRequestException("Lottery ticket is out of stock");
        }

        PaymentService paymentService = new PaymentService();
        if (!paymentService.processPayment(paymentDetails)) {
            throw new BadRequestException("Payment failed");
        }

        lottery.setAmount(lottery.getAmount() - 1);
        lotteryService.updateLotteryAmount(lottery);

        UserTicket userTicket = new UserTicket();
        userTicket.setUsers(user);
        userTicket.setLottery(lottery);
        userTicket.setPurchaseDate(LocalDateTime.now());
        userTicketRepository.save(userTicket);

        return "Purchase and payment successful";
    }




}
