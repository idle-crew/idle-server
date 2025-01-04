package com.idle.server.global.feign.dto.kakao;

import lombok.Builder;

@Builder
public record KakaoTokenRequest(
        String code,
        String clientId,
        String clientSecret,
        String redirectUri,
        String grantType
) {
}
