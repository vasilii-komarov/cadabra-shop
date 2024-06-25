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
    public UserDto findUser(Long id) {
        return repository.findById(id)
            .map(mapper::userToUserDto)
            .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException("Trying to save 'null'.");
        }

        return mapper.userToUserDto(repository.save(mapper.userDtoToUser(dto)));
    }
}
