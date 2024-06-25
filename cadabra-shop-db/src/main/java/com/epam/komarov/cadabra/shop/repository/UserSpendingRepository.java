package com.epam.komarov.cadabra.shop.repository;

import com.epam.komarov.cadabra.shop.entity.UserSpendingEntity;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpendingRepository extends ListCrudRepository<UserSpendingEntity, Long> {

    List<UserSpendingEntity> findBySpendingGreaterThan(Double spending);

}
