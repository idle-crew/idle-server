package com.idle.server.domain.image.service;

import com.idle.server.domain.image.service.implement.ImageCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CommandImageService {
    private final ImageCreator imageCreator;

    public String uploadImage(MultipartFile file, String folderPath) {
        return imageCreator.create(file, folderPath);
    }
}
