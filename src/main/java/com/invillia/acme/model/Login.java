package com.invillia.acme.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class Login {

    private String username;

    private String password;
}
