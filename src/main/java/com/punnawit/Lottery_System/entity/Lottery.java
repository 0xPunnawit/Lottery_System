package com.punnawit.Lottery_System.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lottery", schema = "lottery_schema")
@Getter
@Setter
public class Lottery {

    @Id
    @Column(name = "ticket_id", length = 6)
    private String ticketId;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "amount", nullable = false)
    private int amount;
}
