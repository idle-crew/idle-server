package com.idle.server.global.feign.client;

import com.idle.server.global.feign.dto.google.GoogleInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "googleInfoClient", url = "https://www.googleapis.com/oauth2/v2/userinfo")
public interface GoogleInfoClient {
    @GetMapping("?access_token={TOKEN}")
    GoogleInfoResponse getUserInfo(@PathVariable("TOKEN") String token);
}
