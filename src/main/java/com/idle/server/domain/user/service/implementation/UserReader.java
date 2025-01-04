package com.idle.server.domain.user.service.implementation;

import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.exception.UserNotFoundException;
import com.idle.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReader {

    private final UserRepository userRepository;

    public User readOne(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->  new UserNotFoundException(id));
    }
}
