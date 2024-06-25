package com.epam.komarov.cadabra.shop.controller;

import com.epam.komarov.cadabra.shop.api.UserApi;
import com.epam.komarov.cadabra.shop.dto.UserDto;
import com.epam.komarov.cadabra.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    @GetMapping("/users/{id}")
    public UserDto findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }

    @Override
    @PostMapping("/users")
    public UserDto saveUser(@RequestBody UserDto dto) {
        return userService.saveUser(dto);
    }

}
