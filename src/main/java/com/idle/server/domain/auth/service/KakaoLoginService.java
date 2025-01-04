package com.idle.server.domain.auth.service;

import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.domain.type.Authority;
import com.idle.server.domain.user.domain.type.OauthType;
import com.idle.server.domain.user.repository.UserRepository;
import com.idle.server.global.config.properties.KakaoAuthProperties;
import com.idle.server.global.feign.client.KakaoAuthClient;
import com.idle.server.global.feign.client.KakaoInfoClient;
import com.idle.server.global.feign.dto.kakao.KakaoInfoResponse;
import com.idle.server.global.feign.dto.kakao.KakaoTokenRequest;
import com.idle.server.global.jwt.dto.TokenResponse;
import com.idle.server.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class KakaoLoginService {

    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoInfoClient kakaoInfoClient;
    private final KakaoAuthProperties authProperties;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public TokenResponse login(String code) {
        String kakaoToken = kakaoAuthClient.getkakaoToken("authorization_code", authProperties.getClientId(), authProperties.getClientSecret(), authProperties.getRedirectUri(), code).accessToken();
        KakaoInfoResponse userInfo = kakaoInfoClient.getUserInfo( "Bearer " + kakaoToken);
        User user = saveOrUpadte(userInfo);
        boolean isFirst = user.getIsFirst();

        return jwtProvider.generateToken(user.getId(), user.getEmail(), user.getAuthority().toString(), isFirst);
    }

    private KakaoTokenRequest createRequest(String code) {
        return KakaoTokenRequest.builder()
                .code(code)
                .clientId(authProperties.getClientId())
                .clientSecret(authProperties.getClientSecret())
                .redirectUri(authProperties.getRedirectUri())
                .grantType("authorization_code")
                .build();
    }

    private User saveOrUpadte(KakaoInfoResponse userInfo) {
        Optional<User> user = userRepository.findByEmail(userInfo.kakaoAccount().email());

        if(user.isEmpty()) {
            return userRepository.save(User.builder()
                    .email(userInfo.kakaoAccount().email())
                    .username(userInfo.kakaoAccount().profile().nickname())
                    .authority(Authority.USER)
                    .isFirst(Boolean.TRUE)
                    .oauthType(OauthType.KAKAO)
                    .build());
        }

        return user.get();
    }
}
