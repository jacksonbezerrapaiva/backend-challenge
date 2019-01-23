package com.invillia.acme.controller;

import com.invillia.acme.model.Store;
import com.invillia.acme.exception.NotFoundStoreException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface StoreApi {

    @ApiOperation(value = "Add new store", nickname = "store", notes = "Nova nota", tags={ "store", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/store",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    ResponseEntity<?> newStore(@ApiParam(value = "Add new store" ,required=true )  @Valid @RequestBody Store store);

    @ApiOperation(value = "Store by cnpj", nickname = "cnpj", notes = "cnpj", tags={ "store", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/store/{cnpj}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    ResponseEntity<?> findStoreByCNPJ(@ApiParam(value = "cnpj",required=true) @PathVariable("cnpj") String cnpj) throws NotFoundStoreException;


    @ApiOperation(value = "Update store", nickname = "updateStore", notes = "UpdateStore", tags={ "store", })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer X") })
    @RequestMapping(value = "/store/{cnpj}",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_INVILLIA')")
    ResponseEntity<?> updateStore(@ApiParam(value = "CNPJ",required=true) @PathVariable("cnpj") String cnpj, @ApiParam(value = "Update store" ,required=true ) @Valid @RequestBody Store body) throws NotFoundStoreException;

}
