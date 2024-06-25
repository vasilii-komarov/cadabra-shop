package com.epam.komarov.cadabra.shop.batch.user;

import com.epam.komarov.cadabra.shop.entity.UserEntity;
import com.epam.komarov.cadabra.shop.dto.UserDto;
import com.epam.komarov.cadabra.shop.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoItemProcessor implements ItemProcessor<UserDto, UserEntity> {

    private final UserMapper mapper;

    @Override
    public UserEntity process(UserDto item) throws Exception {
        return mapper.userDtoToUser(item);
    }

}
