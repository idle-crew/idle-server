package com.idle.server.domain.post.controller.dto;

import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.domain.type.Category;
import com.idle.server.domain.post.domain.type.Status;
import com.idle.server.domain.post.domain.type.Type;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record PostListResponse(
        Long id,
        String title,
        String price,
        String division,
        String description,
        Category category,
        Type type,
        Status status,
        String reason,
        List<String> imgUrls
) {
    public static PostListResponse of(Post post) {
        return new PostListResponse(
                post.getId(),
                post.getTitle(),
                post.getPrice(),
                post.getDivision(),
                post.getDescription(),
                post.getCategory(),
                post.getType(),
                post.getStatus(),
                post.getReason(),
                post.getImgUrls()
        );
    }
}
