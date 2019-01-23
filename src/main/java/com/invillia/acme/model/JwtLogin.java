package com.invillia.acme.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtLogin {

    private String username;
    private String password;
}
