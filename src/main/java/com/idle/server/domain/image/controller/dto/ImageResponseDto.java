package com.idle.server.domain.image.controller.dto;

public record ImageResponseDto(
        String url
) {
    public static ImageResponseDto from(String url) {
        return new ImageResponseDto(url);
    }
}
