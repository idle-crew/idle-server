package com.idle.server.global.jwt.util;

import com.idle.server.global.jwt.dto.TokenResponse;
import com.idle.server.global.jwt.properties.JwtProperties;
import static com.idle.server.global.jwt.properties.JwtConstants.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;


@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String generateAccessToken(Long id, String authId, String role) {
        return generateToken(id, authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
    }

    public TokenResponse generateToken(Long id, String authId, String role, Boolean isFirst) {

        String accessToken = generateToken(id, authId, role, ACCESS_KEY.getMessage(), jwtProperties.getAccessExp());
        String refreshToken = generateToken(id, authId, role, REFRESH_KEY.getMessage(), jwtProperties.getRefreshExp());

        return new TokenResponse(accessToken, refreshToken, getExpiredTime(), isFirst);
    }

    private String generateToken(Long id, String authId, String role, String type, Long exp) {
        return Jwts.builder()
                .claim("userId", id)
                .setHeaderParam(TYPE.message, type)
                .claim(ROLE.getMessage(), role)
                .claim(AUTH_ID.getMessage(), authId)
                .signWith(jwtProperties.getSecret(), SignatureAlgorithm.HS256)
                .setExpiration(
                        new Date(System.currentTimeMillis() + exp * 1000)
                )
                .compact();
    }

    public ZonedDateTime getExpiredTime() {
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }

}
