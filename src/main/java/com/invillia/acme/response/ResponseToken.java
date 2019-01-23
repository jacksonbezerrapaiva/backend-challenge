package com.invillia.acme.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class ResponseToken {

    public ResponseToken() {
        this.timestamp = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    private String token;
    private LocalDateTime timestamp;
}
