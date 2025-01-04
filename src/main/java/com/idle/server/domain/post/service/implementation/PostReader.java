package com.idle.server.domain.post.service.implementation;

import com.idle.server.domain.post.controller.dto.PostListResponse;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReader {
    private final PostRepository postRepository;

    public List<PostListResponse> readAll() {
        return postRepository.findAll().stream()
                .map(PostListResponse::of)
                .toList();
    }

    public Post readOne(Long postId) {
        return postRepository.getById(postId);
    }

    public Post findById(Long postId) {
        return postRepository.getById(postId);
    }
}
