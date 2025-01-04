package com.idle.server.global.feign.client;

import com.idle.server.global.feign.dto.kakao.KakaoInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoInfoClient", url = "https://kapi.kakao.com/v2/user/me")
public interface KakaoInfoClient {
    @GetMapping
    KakaoInfoResponse getUserInfo(@RequestHeader(name = "AUTHORIZATION") String token);
}
