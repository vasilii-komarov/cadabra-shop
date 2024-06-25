package com.epam.komarov.cadabra.shop.mapper;

import com.epam.komarov.cadabra.shop.entity.UserEntity;
import com.epam.komarov.cadabra.shop.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(UserEntity entity);
    UserEntity userDtoToUser(UserDto dto);

}
