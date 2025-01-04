package com.idle.server.domain.user.service;

import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryUserService {

    private final UserReader userReader;

    public User readById(Long id) {
        return userReader.readOne(id);
    }
}
