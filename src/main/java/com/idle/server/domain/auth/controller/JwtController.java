package com.idle.server.domain.auth.controller;

import com.idle.server.domain.auth.service.AuthService;
import com.idle.server.global.jwt.dto.RefreshResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt")
public class JwtController {

    private final AuthService authService;

    @PutMapping()
    public RefreshResponse refreshToken(@RequestHeader(value = "Refresh-Token") @NotBlank String refreshToken) {
        return authService.execute(refreshToken);
    }
}
