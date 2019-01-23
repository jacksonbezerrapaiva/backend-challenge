package com.invillia.acme.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class Response {

    public Response() {
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    private String message;
    private LocalDateTime timestamp;

}
