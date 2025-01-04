package com.idle.server.domain.auth.exception;

import com.idle.server.global.exception.IdleException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class UserNotLoginException extends IdleException {
    public UserNotLoginException() {
        super(FORBIDDEN, "유저가 로그인하지 않았습니다.");
    }
}
