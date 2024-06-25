package com.epam.komarov.cadabra.shop.entity;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LotteryParticipantEntity {

    @Id
    private Long userId;

    private UUID giftCardId;

}
