package com.epam.komarov.cadabra.shop.batch.user;

import com.epam.komarov.cadabra.shop.entity.UserEntity;
import com.epam.komarov.cadabra.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityItemWriter implements ItemWriter<UserEntity> {

    private final UserRepository repository;

    @Override
    public void write(Chunk<? extends UserEntity> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }

}
