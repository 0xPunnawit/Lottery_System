package com.punnawit.Lottery_System.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UserTicketService {

    private final UserTicketService userTicketService;

    @GetMapping("/tickets")
    public ResponseEntity<?> getUserTickets(@RequestParam String userId) {
        return ResponseEntity.ok(userTicketService.getUserTickets(userId));
    }

}
