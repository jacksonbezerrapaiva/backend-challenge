package com.invillia.acme.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseErrorLogin {

    private LocalDateTime timestamp;
    private String message;

}
