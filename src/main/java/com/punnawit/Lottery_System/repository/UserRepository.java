package com.punnawit.Lottery_System.repository;

import com.punnawit.Lottery_System.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
