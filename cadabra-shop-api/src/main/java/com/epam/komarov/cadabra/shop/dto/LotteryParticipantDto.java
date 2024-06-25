package com.epam.komarov.cadabra.shop.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LotteryParticipantDto {

    private Long userId;
    private UUID giftCardId;

}
