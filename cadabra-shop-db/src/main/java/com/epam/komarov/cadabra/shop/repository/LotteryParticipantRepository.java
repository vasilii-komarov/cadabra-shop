package com.epam.komarov.cadabra.shop.repository;

import com.epam.komarov.cadabra.shop.entity.LotteryParticipantEntity;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotteryParticipantRepository extends ListCrudRepository<LotteryParticipantEntity, Long> {

    List<LotteryParticipantEntity> findByGiftCardIdIsNotNull();

}
