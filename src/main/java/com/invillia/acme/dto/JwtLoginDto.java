package com.invillia.acme.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class JwtLoginDto {

    @NotNull(message = "{validator.login.username.notnull}")
    private String username;

    @NotNull(message = "{validator.login.password.notnull}")
    private String password;
}
