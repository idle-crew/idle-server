package com.idle.server.domain.post.service.implementation;

import com.idle.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostDeleter {

    private final PostRepository postRepository;

    public void deletePost(Long postId) {
        postRepository.getById(postId);
        postRepository.deleteById(postId);
    }
}
