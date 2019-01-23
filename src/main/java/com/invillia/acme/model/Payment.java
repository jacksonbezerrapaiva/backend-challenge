package com.invillia.acme.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Payment {

    private String id;
    private String numberCard;
    private LocalDateTime datePayment;
    private String orderId;

}
