package com.invillia.acme.controller;

import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.model.Payment;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface PaymentApi {

    @ApiOperation(value = "new Payment", nickname = "newPayment", notes = "newPayment", tags={ "payment", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/payment",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    public ResponseEntity<?> newPayment(@ApiParam(value = "new PaymentDto" ,required=true )@Valid @RequestBody Payment body) throws OrderNotFoundException;

}
