package com.idle.server.domain.user.exception;

import com.idle.server.global.exception.IdleException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends IdleException {
  public UserNotFoundException(String email) {
    super(HttpStatus.NOT_FOUND, email + "이 이메일인 사용자를 찾을 수 없습니다.");
  }

  public UserNotFoundException(Long id) {
    super(HttpStatus.NOT_FOUND, id + "인 사용자를 찾을 수 없습니다.");
  }
}
