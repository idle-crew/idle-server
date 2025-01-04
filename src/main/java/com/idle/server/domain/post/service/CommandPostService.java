package com.idle.server.domain.post.service;

import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.exception.CannotDeleteOthersArticleException;
import com.idle.server.domain.post.service.implementation.PostCreator;
import com.idle.server.domain.post.service.implementation.PostDeleter;
import com.idle.server.domain.post.service.implementation.PostReader;
import com.idle.server.domain.post.service.implementation.PostUpdater;
import com.idle.server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CommandPostService {

    private final PostCreator postCreator;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;
    private final PostReader postReader;

    public void createPost(PostRequest request, Long userId) {
        postCreator.createPost(request, userId);
    }

    public void updatePost(PostRequest postRequest, Long id) {
        postUpdater.updatePost(postRequest, id);
    }

    public void updateImgs(PostRequest postRequest, Long id) {
        postUpdater.updateImgs(postRequest, id);
    }

    public void deletePost(Long id, User currentUser) {
        Post byId = postReader.findById(id);
        if (!Objects.equals(byId.getId(), currentUser.getId())) {
            throw new CannotDeleteOthersArticleException();
        }
        postDeleter.deletePost(id);
    }
}
