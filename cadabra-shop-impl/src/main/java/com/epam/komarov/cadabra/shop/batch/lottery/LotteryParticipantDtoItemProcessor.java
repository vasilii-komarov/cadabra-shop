package com.epam.komarov.cadabra.shop.batch.lottery;

import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import com.epam.komarov.cadabra.shop.dto.LotteryParticipantDto;
import com.epam.komarov.cadabra.shop.mapper.LotteryParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryParticipantDtoItemProcessor
    implements ItemProcessor<LotteryParticipantDto, LotteryParticipantEntity> {

    private final LotteryParticipantMapper mapper;

    @Override
    public LotteryParticipantEntity process(LotteryParticipantDto item) throws Exception {
        return mapper.lotteryParticipantDtoToLotteryParticipant(item);
    }

}
