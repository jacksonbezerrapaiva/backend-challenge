package com.invillia.acme.controller;

import com.invillia.acme.exception.IntervalNotPermitException;
import com.invillia.acme.exception.OrderItemNotFoundException;
import com.invillia.acme.model.Refund;
import com.invillia.acme.model.Store;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface RefundApi {
    @ApiOperation(value = "Add new refund", nickname = "refund", notes = "Nova nota", tags={ "refund", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/refund",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    ResponseEntity<?> newStore(@ApiParam(value = "Add new refund" ,required=true )  @Valid @RequestBody Refund body) throws OrderItemNotFoundException, IntervalNotPermitException;

}
