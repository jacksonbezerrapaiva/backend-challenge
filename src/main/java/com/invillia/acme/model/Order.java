package com.invillia.acme.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class Order {

    @Valid
    private String id;
    private Address address;
    private String status;
    @Valid
    private List<OrderItem> orderItemList;
    private LocalDate confirmationDate;
    private String cnpj;

}
