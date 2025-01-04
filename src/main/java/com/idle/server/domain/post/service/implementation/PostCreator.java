package com.idle.server.domain.post.service.implementation;

import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.repository.PostRepository;
import com.idle.server.domain.user.domain.User;
import com.idle.server.domain.user.exception.UserNotFoundException;
import com.idle.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCreator {

    @Autowired
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void createPost(PostRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Post post = Post.builder()
                .writer(user.getId())
                .title(request.title())
                .price(request.price())
                .division(request.division())
                .description(request.description())
                .category(request.category())
                .type(request.type())
                .status(request.status())
                .reason(request.reason())
                .stack(request.stack())
                .imgUrls(request.imgUrls())
                .build();

        postRepository.save(post);
    }
}
