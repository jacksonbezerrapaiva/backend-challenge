package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.PaymentApi;
import com.invillia.acme.dto.PaymentDto;
import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.model.Payment;
import com.invillia.acme.response.ConvertResponse;
import com.invillia.acme.usecases.CreatePaymentUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
@Api(value = "payment", description = "payment API")
public class PaymentApiController implements PaymentApi {

    private CreatePaymentUseCase createPaymentUseCase;
    private ConvertResponse convertResponse;

    public ResponseEntity<?> newPayment(@ApiParam(value = "new PaymentDto", required = true) @Valid @RequestBody Payment body) throws OrderNotFoundException {
        PaymentDto paymentDto = PaymentDto.builder()
                .datePayment(body.getDatePayment())
                .id(body.getId())
                .numberCard(body.getNumberCard())
                .orderId(body.getOrderId())
                .build();
        createPaymentUseCase.save(paymentDto);
        return new ResponseEntity<>(convertResponse.newPayment(), HttpStatus.CREATED);
    }
}
