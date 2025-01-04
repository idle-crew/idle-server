package com.idle.server.domain.post.service;

import com.idle.server.domain.post.controller.dto.PostListResponse;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.service.implementation.PostReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryPostService {
    private final PostReader postReader;

    public List<PostListResponse> readAllPost() {
        return postReader.readAll();
    }

    public Post readPostById(Long postId) {
        return postReader.readOne(postId);
    }
}
