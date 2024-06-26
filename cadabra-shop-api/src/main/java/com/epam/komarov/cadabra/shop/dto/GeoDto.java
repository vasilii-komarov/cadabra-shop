package com.epam.komarov.cadabra.shop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GeoDto {

    private String lat;
    private String lng;

}
