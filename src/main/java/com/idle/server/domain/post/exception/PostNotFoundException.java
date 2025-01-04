package com.idle.server.domain.post.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends IdleException {
    public PostNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "id가 " + id + "인 게시글을 찾을 수 없습니다.");
    }
}