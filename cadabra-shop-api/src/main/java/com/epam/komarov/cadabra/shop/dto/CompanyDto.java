package com.epam.komarov.cadabra.shop.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyDto {

    private String name;
    private String catchPhrase;
    private String bs;

}
