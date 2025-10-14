package com.punnawit.Lottery_System.service;

import com.punnawit.Lottery_System.dto.response.LotteryPurchaseHistoryResponse;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        // ส่ง response กลับ พร้อมจำนวนที่ซื้อ
        return new LotteryPurchaseResponse(
                lottery.getTicketId(),
                lottery.getPrice(),
                amount,
                totalPrice,
                "Purchase successful"
        );
    }

    public List<LotteryPurchaseHistoryResponse> getUserPurchasedLotteries() {
        // ดึง userId จาก SecurityContext (JWT token)
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Users> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        Users userById = byId.get();

        List<UserTicket> userTickets = userTicketRepository.findByUsers(userById);

        return userTickets.stream().map(userTicket -> {
            return new LotteryPurchaseHistoryResponse(
                    userTicket.getUserTicketId(),
                    userTicket.getLottery().getTicketId(),
                    userTicket.getLottery().getPrice(),
                    userTicket.getAmount(),
                    userTicket.getPurchaseDate()
            );
        }).collect(Collectors.toList());
    }

    @Transactional
    public String cancelLotteryPurchase(String ticketId, int amount) {
        // Fetch userId from SecurityContext (JWT token)
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Check if user exists
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        // Find the lottery by ticketId
        Optional<Lottery> lotteryOpt = lotteryRepository.findByTicketId(ticketId);
        if (lotteryOpt.isEmpty()) {
            throw new ResourceNotFoundException("Lottery not found");
        }

        Lottery lottery = lotteryOpt.get();
        // Find the user's ticket purchase
        Optional<UserTicket> userTicketOpt = userTicketRepository.findByUsersAndLottery(userOpt.get(), lottery);
        if (userTicketOpt.isEmpty()) {
            throw new ResourceNotFoundException("Purchase not found");
        }

        UserTicket userTicket = userTicketOpt.get();

        // Ensure the user is not returning more tickets than they bought
        if (userTicket.getAmount() < amount) {
            throw new IllegalArgumentException("You are attempting to return more tickets than you purchased.");
        }

        // Decrease the number of tickets purchased by the user
        userTicket.setAmount(userTicket.getAmount() - amount);
        userTicketRepository.save(userTicket);

        // Increase the stock of tickets in the lottery
        lottery.setAmount(lottery.getAmount() + amount);
        lotteryRepository.save(lottery);

        return "Purchase cancelled and tickets returned to the lottery stock.";
    }

}
