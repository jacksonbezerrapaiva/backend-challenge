package com.invillia.acme.controller;

import com.invillia.acme.model.JwtLogin;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface AuthApi {

    @ApiOperation(value = "new token", nickname = "token", notes = "Gerar token", tags={ "auth", })
    @RequestMapping(value = "/auth",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<?> auth(@Valid @RequestBody JwtLogin auth);

}
