package com.idle.server.domain.user.controller.dto;

import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.domain.type.Authority;
import com.idle.server.domain.user.domain.type.OauthType;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        String nickname,
        Authority authority,
        OauthType oauthType,
        String position
) {
    public static UserResponseDto from(final User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getNickname(),
                user.getAuthority(),
                user.getOauthType(),
                user.getPosition()
        );
    }
}
