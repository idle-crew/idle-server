package com.idle.server.domain.image.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class S3SaveException extends IdleException {
    public S3SaveException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "사진을 저장하던 중 오류가 발생하였습니다.");
    }
}

