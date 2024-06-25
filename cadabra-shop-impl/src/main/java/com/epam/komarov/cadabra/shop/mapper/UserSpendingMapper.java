package com.epam.komarov.cadabra.shop.mapper;

import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import com.epam.komarov.cadabra.shop.dto.UserSpendingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSpendingMapper {

    UserSpendingDto userSpendingToUserSpendingDto(UserSpendingEntity entity);
    UserSpendingEntity userSpendingDtoToUserSpending(UserSpendingDto dto);

}
