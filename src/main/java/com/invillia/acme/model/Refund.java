package com.invillia.acme.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
public class Refund {

    private String orderItemId;
}
