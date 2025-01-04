package com.idle.server.domain.user.service.implementation;

import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.user.controller.dto.UserRequestDto;
import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.exception.UserNotFoundException;
import com.idle.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserUpdater {
    private final UserRepository userRepository;

    public void updateInfo(UserRequestDto userRequestDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        try {
            if (user.getIsFirst()) {
                user.update();
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        user.updateInfo(userRequestDto);
    }
}
