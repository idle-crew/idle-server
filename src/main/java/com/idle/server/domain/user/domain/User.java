package com.idle.server.domain.user.domain;

import com.idle.server.domain.user.controller.dto.UserRequestDto;
import com.idle.server.domain.user.domain.type.Authority;
import com.idle.server.domain.user.domain.type.OauthType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    private String email;

    private String nickname;

    private Boolean isFirst;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private OauthType oauthType;

    private String position;

    @Builder
    public User(String username, String email, String nickname, Authority authority, Boolean isFirst, OauthType oauthType, String position) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.authority = authority;
        this.isFirst = isFirst;
        this.oauthType = oauthType;
        this.position = position;
    }

    public User update() {
        this.isFirst = Boolean.FALSE;
        return this;
    }

    public void updateInfo(UserRequestDto userRequestDto) {
        this.position = userRequestDto.position();
        this.nickname = userRequestDto.nickname();
    }
}