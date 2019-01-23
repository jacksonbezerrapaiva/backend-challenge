package com.invillia.acme.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItem {

    private String id;
    private Long amount;
    private String description;
    private BigDecimal unitPrice;
}
