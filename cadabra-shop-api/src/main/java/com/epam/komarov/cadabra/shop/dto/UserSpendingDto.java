package com.epam.komarov.cadabra.shop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserSpendingDto {

    private Long userId;
    private Double spending;

}
