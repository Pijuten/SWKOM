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
            log.info("Uploading file: " + file.getOriginalFilename());

            // Make 'paperless' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("paperless").build());
            if (!found) {
                // Make a new bucket called 'paperless'.
                log.info("Creating new bucket 'paperless'");
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("paperless").build());
            } else {
                log.info("'paperless' bucket already exists.");
            }

            String fileName = id.toString();


            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket("paperless").object(fileName).stream(
                                    inputStream, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            inputStream.close();
            log.info("File uploaded successfully: {} for ID: {}", fileName, id);
            return true;

        } catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            log.error("Error occurred during file upload for ID: {}", id,e);
            return false;
        }
    }

    public void deleteFile(String objectName) throws Exception {
        try {
            log.info("Deleting file: {}", objectName);
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("paperless")
                            .object(objectName)
                            .build()
            );
            log.info("File deleted successfully: {}", objectName);
        } catch (MinioException e) {
            log.error("Error occurred during file deletion: {}", objectName, e);
            throw new RuntimeException("Failed to delete file: " + objectName, e);
        }
    }

}