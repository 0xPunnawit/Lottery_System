package com.punnawit.Lottery_System.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users", schema = "lottery_schema")
@Getter
@Setter
public class Users {

    @Id
    @Column(name = "user_id", length = 10)
    @NotNull
    private String userId;

    @Column(name = "email", nullable = false, length = 100)
    @NotNull
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    @NotNull
    @Size(min = 6, max = 255)
    private String password;

    @Column(name = "fullname", nullable = false, length = 100)
    @NotNull
    @Size(min = 3, max = 100)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 10)
    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be a valid 10 digit number")
    private String phone;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role = Roles.USER;
}
