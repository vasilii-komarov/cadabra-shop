package com.epam.komarov.cadabra.shop.entity;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
public class LotteryParticipantEntity {

    @Id
    private Long userId;

    private UUID giftCardId;

}
