package com.epam.komarov.cadabra.shop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressDto {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;

}
