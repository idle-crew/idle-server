package com.idle.server.global.feign.dto.google;

import lombok.Builder;

@Builder
public record GoogleTokenRequest(
        String code,
        String clientId,
        String clientSecret,
        String redirectUri,
        String grantType
) {
}
