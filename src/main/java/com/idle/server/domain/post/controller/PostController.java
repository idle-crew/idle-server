package com.idle.server.domain.post.controller;

import com.idle.server.domain.auth.service.implementation.AuthReader;
import com.idle.server.domain.post.controller.dto.PostListResponse;
import com.idle.server.domain.post.controller.dto.PostRequest;
import com.idle.server.domain.post.domain.Post;
import com.idle.server.domain.post.exception.CanNotSearchException;
import com.idle.server.domain.post.service.CommandPostService;
import com.idle.server.domain.post.service.QueryPostService;
import com.idle.server.domain.user.controller.dto.UserRequestDto;
import com.idle.server.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final CommandPostService commandPostService;
    private final QueryPostService queryPostService;
    private final JwtService jwtService;
    private final AuthReader authReader;

    @PostMapping
    public ResponseEntity<String> create(HttpServletRequest request, @RequestBody PostRequest postRequest) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);

        commandPostService.createPost(postRequest, userId);
        return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> update(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        commandPostService.updatePost(postRequest, postId);
        return ResponseEntity.ok("성공적으로 수정되었습니다.");
    }

    @PutMapping("/img/{postId}")
    public ResponseEntity<String> updateImg(@PathVariable Long postId, @RequestBody PostRequest postRequest) {
        commandPostService.updateImgs(postRequest, postId);
        return ResponseEntity.ok("이미지가 저장되었습니다.");
    }

    @GetMapping("/All")
    private List<PostListResponse> readPost() {
        return queryPostService.readAllPost();
    }

    @GetMapping("/{postId}")
    private Post read(@PathVariable Long postId) {
        return queryPostService.readPostById(postId);
    }

    @DeleteMapping("/{postId}")
    private void delete(@PathVariable Long postId) {
        commandPostService.deletePost(postId, authReader.getCurrentUser());
    }

    @PutMapping("/profile")
    public void updateProfile(HttpServletRequest request,  @RequestBody PostRequest postRequest) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);

        commandPostService.updateImgs(postRequest, userId);
    }
}
