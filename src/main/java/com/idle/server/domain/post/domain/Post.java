package com.idle.server.domain.post.domain;

import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.type.Category;
import com.idle.server.domain.post.domain.type.Status;
import com.idle.server.domain.post.domain.type.Type;
import com.idle.server.domain.user.controller.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String title;

    @Column(length = 6)
    private String price;

    @Column(length = 3)
    private String division;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String reason;

    private String description;

    private String stack;

    private List<String> imgUrls = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createTime;

    @Column(name = "user_id")
    private Long writer;

    @Builder
    public Post(String title, String price, String division, Category category, Type type, Status status, String reason, String description, String stack, Long writer, List<String> imgUrls) {
        this.title = title;
        this.price = price;
        this.division = division;
        this.category = category;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.description = description;
        this.stack = stack;
        this.writer = writer;
        this.imgUrls = imgUrls;
    }

    public void updatePost(PostRequest request) {
        this.title = request.title();
        this.price = request.price();
        this.division = request.division();
        this.description = request.description();
        this.category = request.category();
        this.type = request.type();
        this.status = request.status();
        this.reason = request.reason();
    }

    @PrePersist
    public void createAt() {
        this.createTime = LocalDateTime.now();
    }


    public void updateImg(PostRequest postRequest){
        this.imgUrls = postRequest.imgUrls();
    }
}
