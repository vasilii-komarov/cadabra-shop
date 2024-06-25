package com.epam.komarov.cadabra.shop.api;

import com.epam.komarov.cadabra.shop.dto.UserDto;

public interface UserApi {

    UserDto findUser(Long id);

    UserDto saveUser(UserDto dto);

}
