package com.idle.server.domain.post.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class CannotDeleteOthersArticleException extends IdleException {
    public CannotDeleteOthersArticleException() {
        super(HttpStatus.FORBIDDEN, "다른 사람의 글을 삭제할 수 없습니다.");
    }
}
