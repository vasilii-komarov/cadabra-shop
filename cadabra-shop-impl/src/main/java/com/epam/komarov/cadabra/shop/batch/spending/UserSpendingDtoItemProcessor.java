package com.epam.komarov.cadabra.shop.batch.spending;

import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.dto.UserSpendingDto;
import com.epam.komarov.cadabra.shop.mapper.UserSpendingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSpendingDtoItemProcessor implements ItemProcessor<UserSpendingDto, UserSpendingEntity> {

    private final UserSpendingMapper mapper;

    @Override
    public UserSpendingEntity process(UserSpendingDto item) throws Exception {
        return mapper.userSpendingDtoToUserSpending(item);
    }

}
