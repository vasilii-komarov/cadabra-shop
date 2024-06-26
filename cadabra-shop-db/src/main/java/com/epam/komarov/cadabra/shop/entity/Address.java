package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Embeddable
@Accessors(chain = true)
public class Address {

    private String street;

    private String suite;

    private String city;

    private String zipcode;

    @Embedded
    private Geo geo;

}
