package org.openapitools.api;

import io.minio.*;
import org.openapitools.services.RabbitMQSenderService;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentContentRepository documentContentRepository;
    private final RabbitMQSenderService rabbitMQSenderService;
    private final MinioClient minioClient;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentContentRepository documentContentRepository, RabbitMQSenderService rabbitMQSenderService, MinioClient minioClient) {
        this.documentRepository = documentRepository;
        this.documentContentRepository = documentContentRepository;

        this.rabbitMQSenderService = rabbitMQSenderService;
        this.minioClient = minioClient;
    }
    public List<DocumentDto> getDocuments() {
        return new ArrayList<>(documentRepository.findAll()
        );
    }

    public DocumentDto getDocumentById(UUID documentId) {
        return documentRepository.findById(documentId).orElse(null);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
       DocumentDto createdDocumentDto = documentRepository.save(documentDto);
       rabbitMQSenderService.sendToOcrQueue(createdDocumentDto.getId().toString());
       return createdDocumentDto;
    }
    public Void deleteDocumentById(UUID documentId) {
        documentRepository.deleteById(documentId);
        return null;
    }

    public InputStream getDocumentContent(UUID documentId) {
        DocumentContentDto documentContent = documentContentRepository.findById(documentId).orElseThrow(
                () -> new RuntimeException("Document content not found for ID: " + documentId)
        );

        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(documentContent.getBucketName())
                            .object(documentContent.getObjectName())
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving document content", e);
        }
    }
    public String saveDocumentContent(UUID documentId, InputStream fileContent, String contentType){
        try {
            String bucketName = "documents"; // Define your bucket name
            String objectName = UUID.randomUUID().toString(); // Generate a unique name for the file

            // Create bucket if it doesn't exist
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(bucketName).build()
            );
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Upload file to MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(fileContent, fileContent.available(), -1)
                            .contentType(contentType)
                            .build()
            );

            // Save metadata (bucket and object info) in the database
            DocumentContentDto documentContent = new DocumentContentDto();
            documentContent.setId(documentId);
            documentContent.setBucketName(bucketName);
            documentContent.setObjectName(objectName);
            documentContent.setContentType(contentType);

            return "File uploaded successfully!";
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while saving document content", e);
        }
    }
    @RabbitListener(queues = "result_queue")
    public String receiveDocumentCreated (String message) {
        return message;
    }
}
