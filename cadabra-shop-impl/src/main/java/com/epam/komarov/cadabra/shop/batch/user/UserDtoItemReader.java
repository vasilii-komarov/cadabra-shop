package com.epam.komarov.cadabra.shop.batch.user;

import com.epam.komarov.cadabra.shop.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDtoItemReader implements ItemReader<UserDto> {

    private final RestTemplate restTemplate;

    @Value("${api.users.url:https://jsonplaceholder.typicode.com/users}")
    private String usersUrl;

    private List<UserDto> users;
    private int userIndex = 0;

    @Override
    public UserDto read() {
        if (usersAreNotFetched()) {
            List<UserDto> response = getUsersFromApi();
            if (Objects.isNull(response)) {
                log.warn("No users for processing.");
                return null;
            }
            users = new ArrayList<>(response);
        }

        return getNextUserDto();
    }

    private List<UserDto> getUsersFromApi() {
        ResponseEntity<List<UserDto>> apiResponse = restTemplate.exchange(usersUrl, HttpMethod.GET,
            null, new ParameterizedTypeReference<>() {});
        if (!HttpStatus.OK.equals(apiResponse.getStatusCode())) {
            throw new NonTransientResourceException(
                "Error fetching users. API response status is not '200': %s - %s".formatted(
                    apiResponse.getStatusCode(), apiResponse.getBody())
            );
        }
        return apiResponse.getBody();
    }

    private boolean usersAreNotFetched() {
        return Objects.isNull(users);
    }

    private UserDto getNextUserDto() {
        UserDto user = null;

        if (userIndex < users.size()) {
            user = users.get(userIndex);
            userIndex++;
        }
        else {
            userIndex = 0;
            users = null;
        }
        
        return user;
    }

}
