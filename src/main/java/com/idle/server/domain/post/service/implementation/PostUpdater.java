package com.idle.server.domain.post.service.implementation;

import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostUpdater {

    private final PostRepository postRepository;

    public void updatePost(PostRequest postRequest, Long id) {
        Post post = postRepository.getById(id);
        post.updatePost(postRequest);
    }

    public void updateImgs(PostRequest postRequest, Long id) {
        Post post = postRepository.getById(id);
        post.updateImg(postRequest);
    }
}
