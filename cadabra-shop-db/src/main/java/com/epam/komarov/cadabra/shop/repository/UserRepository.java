package com.epam.komarov.cadabra.shop.repository;

import com.epam.komarov.cadabra.shop.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Long> {

}
