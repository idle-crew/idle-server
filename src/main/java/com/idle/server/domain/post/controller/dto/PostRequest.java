package com.idle.server.domain.post.controller.dto;

import com.idle.server.domain.post.domain.type.Category;
import com.idle.server.domain.post.domain.type.Status;
import com.idle.server.domain.post.domain.type.Type;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PostRequest (
        @NotNull(message = "제목은 필수 값입니다.")
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
){
}
