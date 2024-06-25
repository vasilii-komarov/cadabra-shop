package com.epam.komarov.cadabra.shop.api;

import com.epam.komarov.cadabra.shop.dto.UserDto;

public interface UserApi { //todo add necessary methods

    UserDto findUser(Long id);

    UserDto saveUser(UserDto dto);

}
