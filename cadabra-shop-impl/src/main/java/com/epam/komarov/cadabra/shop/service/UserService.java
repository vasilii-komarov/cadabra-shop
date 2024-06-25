package com.epam.komarov.cadabra.shop.service;

import com.epam.komarov.cadabra.shop.dto.UserDto;

public interface UserService {

    UserDto findUser(Long id);
    UserDto saveUser(UserDto dto);

}
