package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Embeddable
@Accessors(chain = true)
public class Geo {

    private String lat;
    private String lng;

}
