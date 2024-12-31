package com.idle.server.common.logging;

import com.idle.server.common.exception.IdleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Slf4j
public class LoggingUtils {
    public static void warn(IdleException exception) {
        String message = getExceptionMessage(exception.getMessage());
        log.warn(message + "\n \t {}", exception);
    }

    public static void warn(MethodArgumentNotValidException exception) {
        String message = getExceptionMessage(exception.getMessage());
        log.warn(message + "\n \t {}", exception);
    }

    private static String getExceptionMessage(String message) {
        if (message == null || message.isBlank()) {
            return "";
        }
        return message;
    }

    public static void error(RuntimeException exception) {
        String message = getExceptionMessage(exception.getMessage());
        log.error(message + "\n \t {}", exception);
    }
}
