package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserSpendingEntity {

    @Id
    private Long userId;

    private Double spending;

}
