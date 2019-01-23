package com.invillia.acme.controller.impl;


import com.invillia.acme.controller.RefundApi;
import com.invillia.acme.exception.IntervalNotPermitException;
import com.invillia.acme.exception.OrderItemNotFoundException;
import com.invillia.acme.model.Refund;
import com.invillia.acme.response.ConvertResponse;
import com.invillia.acme.usecases.RefundUseCase;
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
@Api(value = "refund", description = "Refund API")
public class RefundApiController implements RefundApi {

    private RefundUseCase refundUseCase;
    private ConvertResponse convertResponse;

    public ResponseEntity<?> newStore(@ApiParam(value = "Add new refund" ,required=true )  @Valid @RequestBody Refund body) throws OrderItemNotFoundException, IntervalNotPermitException {
        refundUseCase.refund(body.getOrderItemId());
        return new ResponseEntity<>(convertResponse.newRefund(), HttpStatus.CREATED);
    }
}
