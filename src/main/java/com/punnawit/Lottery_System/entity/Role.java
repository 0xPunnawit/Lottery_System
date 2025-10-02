package com.punnawit.Lottery_System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles", schema = "lottery_schema")
@Getter
@Setter
public class Role {

    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", nullable = false, length = 20)
    @NotNull
    @Size(min = 3, max = 20)
    private String roleName;
}
