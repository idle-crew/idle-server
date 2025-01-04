package com.idle.server.global.feign.client;

import com.idle.server.global.feign.dto.kakao.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com/oauth/token")
public interface KakaoAuthClient {
    @PostMapping
    KakaoTokenResponse getkakaoToken(@RequestParam("grant_type") String grantType,
                                     @RequestParam("client_id") String clientId,
                                     @RequestParam("client_secret") String clientSecret,
                                     @RequestParam("redirect_uri") String redirectUri,
                                     @RequestParam("code") String code);
}
