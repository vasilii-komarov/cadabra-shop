package com.epam.komarov.cadabra.shop.api;

import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import java.util.List;

public interface LotteryParticipantApi { //todo add necessary methods

    List<LotteryParticipantDto> getLotteryParticipants();

    List<LotteryParticipantDto> getLotteryWinners();

}
