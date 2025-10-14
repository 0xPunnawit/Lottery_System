package com.punnawit.Lottery_System.controller;

import com.punnawit.Lottery_System.business.UserBusiness;
import com.punnawit.Lottery_System.dto.request.UpdateFullNameRequest;
import com.punnawit.Lottery_System.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserBusiness userBusiness;

    @PutMapping("/update")
    public ResponseEntity<String> updateFullName(
            @Valid @RequestBody UpdateFullNameRequest request
    ) {
        Users updatedUser = userBusiness.updateFullName(request);

        return ResponseEntity.status(HttpStatus.OK).body("Full name updated successfully");
    }
}
