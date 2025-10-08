package com.punnawit.Lottery_System.repository;

import com.punnawit.Lottery_System.entity.UserTicket;
import com.punnawit.Lottery_System.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {

    List<UserTicket> findByUsers(Users user);
}
