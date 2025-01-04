package com.idle.server.domain.post.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class CanNotSearchException extends IdleException {
    public CanNotSearchException() {
        super(HttpStatus.NOT_FOUND, "검색된 결과가 없습니다.");
    }
}
