package com.punnawit.Lottery_System.dto.response;


import com.punnawit.Lottery_System.entity.Lottery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LotteryResponse {

    private List<Lottery> tickets;
}
