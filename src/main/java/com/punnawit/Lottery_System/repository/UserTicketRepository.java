package com.punnawit.Lottery_System.repository;

import com.punnawit.Lottery_System.entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}
