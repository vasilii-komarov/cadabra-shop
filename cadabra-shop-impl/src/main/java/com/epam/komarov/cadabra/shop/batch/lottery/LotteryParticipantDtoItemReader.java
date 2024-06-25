package com.epam.komarov.cadabra.shop.batch.lottery;

import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import com.epam.komarov.cadabra.shop.service.LotteryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LotteryParticipantDtoItemReader implements ItemReader<LotteryParticipantDto> {

    private final LotteryService lotteryService;

    private List<LotteryParticipantDto> lotteryParticipants;
    private int lotteryParticipantIndex = 0;

    @Override
    public LotteryParticipantDto read() {
        if (lotteryParticipantsAreNotFetched()) {
            List<LotteryParticipantDto> participants = lotteryService.selectLotteryParticipants();
            if (Objects.isNull(participants)) {
                log.warn("No Lottery participants for processing.");
                return null;
            }
            lotteryParticipants = new ArrayList<>(participants);
        }

        return getNextLotteryParticipantDto();
    }

    private boolean lotteryParticipantsAreNotFetched() {
        return Objects.isNull(lotteryParticipants);
    }

    private LotteryParticipantDto getNextLotteryParticipantDto() {
        LotteryParticipantDto participant = null;

        if (lotteryParticipantIndex < lotteryParticipants.size()) {
            participant = lotteryParticipants.get(lotteryParticipantIndex);
            lotteryParticipantIndex++;
        }
        else {
            lotteryParticipantIndex = 0;
            lotteryParticipants = null;
        }

        return participant;
    }

}
