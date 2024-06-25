package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserEntity {

    @Id
    private Long id;

    private String name;

    private String username;

    private String email;

    @Embedded
    @AttributeOverride(name = "street", column = @Column(name = "address_street"))
    @AttributeOverride(name = "suite", column = @Column(name = "address_suite"))
    @AttributeOverride(name = "city", column = @Column(name = "address_city"))
    @AttributeOverride(name = "zipcode", column = @Column(name = "address_zipcode"))
    @AttributeOverride(name = "geo.lat", column = @Column(name = "address_geo_lat"))
    @AttributeOverride(name = "geo.lng", column = @Column(name = "address_geo_lng"))
    private Address address;

    private String phone;

    private String website;

    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "company_name"))
    @AttributeOverride(name = "catchPhrase", column = @Column(name = "company_catch_phrase"))
    @AttributeOverride(name = "bs", column = @Column(name = "company_bs"))
    private Company company;

}
