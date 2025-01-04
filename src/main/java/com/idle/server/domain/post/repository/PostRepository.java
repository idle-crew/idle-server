package com.idle.server.domain.post.repository;

import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.exception.PostNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

    default Post getById(Long id) {
        return findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));
    }
}