package com.invillia.acme.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Validated
public class PaymentDto {

    @NotNull(message = "{validator.payment.id.notnull}")
    @Size(min=1,max=10, message = "{validator.payment.id.min.max}")
    private String id;

    @NotNull(message = "{validator.payment.numberCard.notnull}")
    @Size(min=16,max=16, message = "{validator.payment.numberCard.min.max}")
    private String numberCard;

    @NotNull(message = "{validator.payment.datePayment.notnull}")
    private LocalDateTime datePayment;

    @NotNull(message = "{validator.payment.orderId.notnull}")
    private String orderId;

}
