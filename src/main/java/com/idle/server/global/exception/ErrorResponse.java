package com.idle.server.global.exception;

public record ErrorResponse(
        int status,
        String message

) {
    @Override
    public String toString() {
        return "{\n" +
                "\t\"status\": " + status +
                ",\n\t\"message\": \"" + message + '\"' +
                "\n}";
    }
}