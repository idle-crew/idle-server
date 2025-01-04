package com.idle.server.domain.user.controller;

import com.idle.server.domain.auth.service.implementation.AuthReader;
import com.idle.server.domain.user.controller.dto.UserRequestDto;
import com.idle.server.domain.user.controller.dto.UserResponseDto;
import com.idle.server.domain.user.service.CommandUserService;
import com.idle.server.domain.user.service.QueryUserService;
import com.idle.server.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;

    private final JwtService jwtService;
    private final AuthReader authReader;

    @GetMapping
    public UserResponseDto readById(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);

        return UserResponseDto.from(queryUserService.readById(userId));
    }

    @PutMapping("/userInfo")
    public ResponseEntity<String> updateInfo(HttpServletRequest request, @RequestBody UserRequestDto userRequestDto) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);

        commandUserService.update(userRequestDto, userId);
        return ResponseEntity.ok("유저 정보가 수정되었습니다.");
    }

    @DeleteMapping
    public void deleteById() {
        commandUserService.delete(authReader.getCurrentUser());
    }
}
