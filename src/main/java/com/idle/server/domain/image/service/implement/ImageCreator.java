package com.idle.server.domain.image.service.implement;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.idle.server.domain.image.exception.S3SaveException;
import com.idle.server.global.config.S3Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageCreator {
    private final S3Bucket s3Bucket;
    private final AmazonS3 amazonS3;

    public String create(MultipartFile multipartFile, String folderPath) {
        String fileName = createFileName(multipartFile, folderPath);

        try {
            PutObjectRequest request = new PutObjectRequest(
                    s3Bucket.getS3Bucket(),
                    fileName,
                    multipartFile.getInputStream(),
                    getMetadata(multipartFile)
            );

            amazonS3.putObject(request);
        } catch (IOException e) {
            throw new S3SaveException();
        }

        return amazonS3.getUrl(s3Bucket.getS3Bucket(), fileName).toString();
    }

    private String createFileName(MultipartFile multipartFile, String folderPath) {
        return folderPath + "/" + multipartFile.getOriginalFilename() + "-" + UUID.randomUUID();
    }

    private ObjectMetadata getMetadata(MultipartFile file) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        return metadata;
    }
}
