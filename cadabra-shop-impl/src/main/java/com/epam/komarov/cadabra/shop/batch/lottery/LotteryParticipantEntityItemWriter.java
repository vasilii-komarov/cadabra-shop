package com.epam.komarov.cadabra.shop.batch.lottery;

import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import com.epam.komarov.cadabra.shop.repository.LotteryParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LotteryParticipantEntityItemWriter implements ItemWriter<LotteryParticipantEntity> {

    private final LotteryParticipantRepository repository;

    @Override
    public void write(Chunk<? extends LotteryParticipantEntity> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }

}
