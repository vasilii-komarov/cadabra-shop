package com.epam.komarov.cadabra.shop.service;

import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import java.util.List;

public interface LotteryService {

    List<LotteryParticipantDto> selectLotteryParticipants();

    List<LotteryParticipantDto> getLotteryParticipants();

    List<LotteryParticipantDto> getLotteryWinners();

}
