package com.idle.server.domain.auth.service;

import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.domain.type.Authority;
import com.idle.server.domain.user.domain.type.OauthType;
import com.idle.server.domain.user.repository.UserRepository;
import com.idle.server.global.config.properties.GoogleAuthProperties;
import com.idle.server.global.feign.client.GoogleAuthClient;
import com.idle.server.global.feign.client.GoogleInfoClient;
import com.idle.server.global.feign.dto.google.GoogleInfoResponse;
import com.idle.server.global.feign.dto.google.GoogleTokenRequest;
import com.idle.server.global.jwt.dto.TokenResponse;
import com.idle.server.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoogleLoginService {

    private final GoogleAuthClient googleAuthClient;
    private final GoogleInfoClient googleInfoClient;
    private final GoogleAuthProperties authProperties;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public TokenResponse login(String code) {
        String googleToken = googleAuthClient.getGoogleToken(createRequest(code)).accessToken();
        GoogleInfoResponse userInfo = googleInfoClient.getUserInfo(googleToken);
        User user = saveOrUpdate(userInfo);
        boolean isFirst = user.getIsFirst();

        return jwtProvider.generateToken(user.getId(), user.getEmail(), user.getAuthority().toString(), isFirst);
    }


    private GoogleTokenRequest createRequest(String code) {
        return GoogleTokenRequest.builder()
                .code(code)
                .clientId(authProperties.getClientId())
                .clientSecret(authProperties.getClientSecret())
                .redirectUri(authProperties.getRedirectUri())
                .grantType("authorization_code")
                .build();
    }

    private User saveOrUpdate(GoogleInfoResponse userInfo) {
        Optional<User> user = userRepository.findByEmail(userInfo.email());

        if (user.isEmpty()) {
            return userRepository.save(User.builder()
                    .email(userInfo.email())
                    .username(userInfo.name())
                    .authority(Authority.USER)
                    .isFirst(Boolean.TRUE)
                    .oauthType(OauthType.GOOGLE)
                    .build());
        }

        return user.get();
    }
}
