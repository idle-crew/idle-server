package com.idle.server.domain.auth.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class TokenInvalidException extends IdleException {
    public TokenInvalidException() {
        super(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");
    }
}
