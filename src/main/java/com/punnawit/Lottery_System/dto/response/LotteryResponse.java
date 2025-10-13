package com.punnawit.Lottery_System.dto.response;


import com.punnawit.Lottery_System.entity.Lottery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LotteryResponse {

    private List<Lottery> tickets;
}
