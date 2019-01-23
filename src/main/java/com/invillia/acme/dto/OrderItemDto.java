package com.invillia.acme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Validated
public class OrderItemDto {

    @NotNull(message = "{validator.order.item.id.notnull}")
    @Size(min=1,max=10, message = "{validator.order.item.id.min.max}")
    private String id;

    @NotNull(message = "{validator.order.item.amount.notnull}")
    private Long amount;

    @NotNull(message = "{validator.order.item.description.notnull}")
    @Size(min=1,max=128, message = "{validator.order.item.description.min.max}")
    private String description;

    @NotNull(message = "{validator.order.item.unitPrice.notnull}")
    private BigDecimal unitPrice;
}
