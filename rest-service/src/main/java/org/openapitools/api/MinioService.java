package org.openapitools.api;

import io.minio.*;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Slf4j
@Component
public class MinioService {

    @Autowired
    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }
    private final MinioClient minioClient;

    public boolean upload(MultipartFile file, UUID id) {
        try {

            // Make 'paperless' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("paperless").build());
            if (!found) {
                // Make a new bucket called 'paperless'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("paperless").build());
            } else {
                System.out.println("Bucket 'paperless' already exists.");
            }

            String fileName = id.toString();


            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("paperless").object(fileName).stream(
                                    inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            inputStream.close();
        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            System.out.println("Error occurred: " + e);
            return false;
        }
        System.out.println("File uploaded: " + file.getOriginalFilename());
        return true;
    }
    public void deleteFile(String objectName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket("paperless")
                        .object(objectName)
                        .build()
        );
        log.info("File deleted: " + objectName);
    }

}