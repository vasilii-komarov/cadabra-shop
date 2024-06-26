package com.epam.komarov.cadabra.shop.dto;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LotteryParticipantDto {

    private Long userId;
    private UUID giftCardId;

}
