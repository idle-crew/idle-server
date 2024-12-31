package com.idle.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class IdleException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
