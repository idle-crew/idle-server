package com.idle.server.domain.auth.controller;

import com.idle.server.domain.auth.service.GoogleLoginService;
import com.idle.server.domain.auth.service.KakaoLoginService;
import com.idle.server.global.jwt.dto.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final GoogleLoginService googleLoginService;
    private final KakaoLoginService kakaoLoginService;

    @PostMapping("/login/google")
    public TokenResponse loginOfGoogle(@Valid @RequestParam(name = "code") String code)  {
         return googleLoginService.login(code);
    }

    @PostMapping("/login/kakao")
    public TokenResponse loginOfKakao(@Valid @RequestParam(name = "code") String code) {
        return kakaoLoginService.login(code);
    }
}
