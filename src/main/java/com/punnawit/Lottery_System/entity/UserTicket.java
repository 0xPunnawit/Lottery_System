package com.punnawit.Lottery_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_ticket", schema = "lottery_schema")
@Getter
@Setter
public class UserTicket {

    @Id
    @Column(name = "user_ticket_id")
    private Long userTicketId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id", nullable = false)
    private Lottery lottery;

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;
}
