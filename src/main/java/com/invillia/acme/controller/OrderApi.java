package com.invillia.acme.controller;

import com.invillia.acme.exception.CnpjNotFoundException;
import com.invillia.acme.exception.OrderNotFoundException;
import com.invillia.acme.model.Order;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface OrderApi {

    @ApiOperation(value = "Add new order", nickname = "newOrder", notes = "New OrderDto", tags={ "order", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/order",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    public ResponseEntity<?> newOrder(@ApiParam(value = "New OrderDto" ,required=true )  @Valid @RequestBody Order body) throws CnpjNotFoundException;

    @ApiOperation(value = "Order by id", nickname = "id", notes = "id", tags={ "order", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/order/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    public ResponseEntity<?> findOrderById(@ApiParam(value = "id",required=true) @PathVariable("id") String id) throws OrderNotFoundException;


}
