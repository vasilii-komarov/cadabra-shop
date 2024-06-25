package com.epam.komarov.cadabra.shop.dto;

import lombok.Data;

@Data
public class AddressDto {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDto geo;

}
