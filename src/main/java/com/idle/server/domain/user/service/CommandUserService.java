package com.idle.server.domain.user.service;

import com.idle.server.domain.auth.service.implementation.AuthReader;
import com.idle.server.domain.user.controller.dto.UserRequestDto;
import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.service.implementation.UserDeleter;
import com.idle.server.domain.user.service.implementation.UserUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandUserService {
    private final UserDeleter userDeleter;
    private final UserUpdater userUpdater;

    public void delete(User id) {
        userDeleter.delete(id);
    }

    public void update(UserRequestDto userRequestDto, Long id) {
        userUpdater.updateInfo(userRequestDto, id);
    }
}
