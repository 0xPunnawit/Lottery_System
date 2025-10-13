package com.punnawit.Lottery_System.repository;

import com.punnawit.Lottery_System.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LotteryRepository extends JpaRepository<Lottery, String> {

    Optional<Lottery> findByTicketId(String ticketId);
}
