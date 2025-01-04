package com.idle.server.domain.auth.service.implementation;

import com.idle.server.domain.auth.repository.AuthRepository;
import com.idle.server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthReader {
    private final AuthRepository authRepository;

    public User getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}