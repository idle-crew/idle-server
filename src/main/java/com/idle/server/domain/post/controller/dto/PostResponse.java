package com.idle.server.domain.post.controller.dto;

import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.domain.type.Category;
import com.idle.server.domain.post.domain.type.Status;
import com.idle.server.domain.post.domain.type.Type;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse(
        Long id,
        Long writer,
        String title,
        String price,
        String division,
        String description,
        Category category,
        Type type,
        Status status,
        String reason,
        String stack,
        List<String> imgUrls
) {
    public static PostResponse of(Post post) {
        return new PostResponse(
                post.getId(),
                post.getWriter(),
                post.getTitle(),
                post.getPrice(),
                post.getDivision(),
                post.getDescription(),
                post.getCategory(),
                post.getType(),
                post.getStatus(),
                post.getReason(),
                post.getStack(),
                post.getImgUrls()
        );
    }
}
