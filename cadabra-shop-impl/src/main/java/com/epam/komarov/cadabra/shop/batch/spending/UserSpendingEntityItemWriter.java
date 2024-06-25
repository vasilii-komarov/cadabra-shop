package com.epam.komarov.cadabra.shop.batch.spending;

import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.repository.UserSpendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSpendingEntityItemWriter implements ItemWriter<UserSpendingEntity> {

    private final UserSpendingRepository repository;

    @Override
    public void write(Chunk<? extends UserSpendingEntity> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }

}
