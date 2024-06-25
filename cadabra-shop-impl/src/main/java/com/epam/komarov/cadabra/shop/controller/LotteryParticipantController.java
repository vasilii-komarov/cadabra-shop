package com.epam.komarov.cadabra.shop.controller;

import com.epam.komarov.cadabra.shop.api.LotteryParticipantApi;
import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import com.epam.komarov.cadabra.shop.service.LotteryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LotteryParticipantController implements LotteryParticipantApi {

    private final LotteryService lotteryService;

    @Override
    @GetMapping("/lottery-participants")
    public List<LotteryParticipantDto> getLotteryParticipants() {
        return lotteryService.getLotteryParticipants();
    }

    @Override
    @GetMapping("/lottery-participants/winners")
    public List<LotteryParticipantDto> getLotteryWinners() {
        return lotteryService.getLotteryWinners();
    }

}
