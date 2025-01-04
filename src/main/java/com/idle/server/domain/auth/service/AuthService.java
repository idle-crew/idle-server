package com.idle.server.domain.auth.service;

import com.idle.server.domain.auth.exception.TokenInvalidException;
import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.repository.UserRepository;
import com.idle.server.global.jwt.dto.RefreshResponse;
import com.idle.server.global.jwt.properties.JwtConstants;
import com.idle.server.global.jwt.util.JwtProvider;
import com.idle.server.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final JwtProvider jwtProvider;

    public RefreshResponse execute(String token) {

        String email;

        try {
            email = jwtUtil.getJwt(jwtUtil.parseToken(token)).getBody().get(JwtConstants.AUTH_ID.message).toString();
        } catch (Exception e) {
            throw new TokenInvalidException();
        }

        Optional<User> user = userRepository.findByEmail(email);
        String newAccessToken = jwtProvider.generateAccessToken(user.get().getId(), user.get().getAuthority().toString(), String.valueOf(false));

        return RefreshResponse.builder()
                .accessToken(newAccessToken)
                .build();
    }
}
