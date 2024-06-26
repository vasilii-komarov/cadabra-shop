package com.epam.komarov.cadabra.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
public class UserSpendingEntity {

    @Id
    private Long userId;

    private Double spending;

}
