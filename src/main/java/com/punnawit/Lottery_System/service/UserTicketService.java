package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.response.LotteryPurchaseResponse;
import com.punnawit.Lottery_System.entity.Lottery;
import com.punnawit.Lottery_System.entity.UserTicket;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.exception.ResourceNotFoundException;
import com.punnawit.Lottery_System.repository.LotteryRepository;
import com.punnawit.Lottery_System.repository.UserRepository;
import com.punnawit.Lottery_System.repository.UserTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTicketService {

    private final UserRepository userRepository;
    private final LotteryRepository lotteryRepository;
    private final UserTicketRepository userTicketRepository;

    @Transactional
    public LotteryPurchaseResponse purchaseLottery(String ticketId, int amount) {
        // ดึง userId จาก SecurityContext (JWT token)
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // ตรวจสอบว่า userId มีอยู่ในระบบหรือไม่
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        // ค้นหาลอตเตอรี่จาก ticketId
        Optional<Lottery> lotteryOpt = lotteryRepository.findByTicketId(ticketId);
        if (lotteryOpt.isEmpty()) {
            throw new ResourceNotFoundException("Lottery not found");
        }

        // ตรวจสอบจำนวนสลากที่เหลือ
        Lottery lottery = lotteryOpt.get();
        if (lottery.getAmount() < amount) {
            throw new ResourceNotFoundException("Not enough lottery tickets in stock");
        }

        // ตรวจสอบว่า user เคยซื้อสลากนี้แล้วหรือยัง
        Optional<UserTicket> existingTicket = userTicketRepository.findByUsersAndLottery(userOpt.get(), lottery);
        if (existingTicket.isPresent()) {
            // หากมีแล้ว อัปเดตจำนวนสลากที่ซื้อ
            UserTicket userTicket = existingTicket.get();
            userTicket.setAmount(userTicket.getAmount() + amount); // เพิ่มจำนวนที่ซื้อ
            userTicket.setPurchaseDate(java.time.LocalDateTime.now());
            userTicketRepository.save(userTicket);
        } else {
            // หากยังไม่เคยซื้อ สร้างใหม่
            UserTicket userTicket = new UserTicket();
            userTicket.setUsers(userOpt.get());
            userTicket.setLottery(lottery);
            userTicket.setAmount(amount); // เก็บจำนวนที่ซื้อ
            userTicket.setPurchaseDate(java.time.LocalDateTime.now());
            userTicketRepository.save(userTicket);
        }

        // อัปเดตจำนวนสลากที่เหลือ
        lottery.setAmount(lottery.getAmount() - amount);
        lotteryRepository.save(lottery);

        // คำนวณราคาทั้งหมดที่ต้องจ่าย (amount * price)
        BigDecimal totalPrice = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(lottery.getPrice()));

        // ส่ง response กลับ พร้อมราคาที่ต้องจ่าย
        return new LotteryPurchaseResponse(
                lottery.getTicketId(),
                lottery.getPrice(),
                lottery.getAmount(),
                totalPrice,
                "Purchase successful"
        );
    }

}
