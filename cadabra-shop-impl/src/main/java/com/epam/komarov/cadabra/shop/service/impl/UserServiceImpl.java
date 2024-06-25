package com.epam.komarov.cadabra.shop.service.impl;

import com.epam.komarov.cadabra.shop.dto.UserDto;
import com.epam.komarov.cadabra.shop.mapper.UserMapper;
import com.epam.komarov.cadabra.shop.repository.UserRepository;
import com.epam.komarov.cadabra.shop.service.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto findUser(Long id) {//todo add validation
        return repository.findById(id)
            .map(mapper::userToUserDto)
            .orElseThrow(() -> new RuntimeException("User not found.")); //todo custom exception
    }

    @Override
    public UserDto saveUser(UserDto dto) {//todo add validation
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Trying to save 'null'."); //todo custom exception
        }

        return mapper.userToUserDto(repository.save(mapper.userDtoToUser(dto))); //todo refactor
    }
}
