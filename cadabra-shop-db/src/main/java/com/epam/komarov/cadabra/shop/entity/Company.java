package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Embeddable
@Accessors(chain = true)
public class Company {

    private String name;
    private String catchPhrase;
    private String bs;

}
