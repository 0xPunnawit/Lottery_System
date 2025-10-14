package com.punnawit.Lottery_System.business;

import com.punnawit.Lottery_System.dto.request.UpdateFullNameRequest;
import com.punnawit.Lottery_System.entity.Users;
import com.punnawit.Lottery_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;

    // ฟังก์ชันอัพเดตชื่อผู้ใช้
    public Users updateFullName(UpdateFullNameRequest request) {
        return userService.updateFullName(request);
    }
}
