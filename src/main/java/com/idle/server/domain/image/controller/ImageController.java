package com.idle.server.domain.image.controller;

import com.idle.server.domain.image.controller.dto.ImageResponseDto;
import com.idle.server.domain.image.service.CommandImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final CommandImageService commandImageService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public ImageResponseDto uploadPlanImage(MultipartFile file) {
        return ImageResponseDto.from(
                commandImageService.uploadImage(file, "idle-PostImages")
        );
    }
}