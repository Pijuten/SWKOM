package org.openapitools.api;

import io.minio.*;
import org.openapitools.repositories.elasticsearch.DocumentElasticsearchRepository;
import org.openapitools.repositories.jpa.DocumentContentJPARepository;
import org.openapitools.repositories.jpa.DocumentJPARepository;
import org.openapitools.services.RabbitMQSenderService;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private final DocumentJPARepository documentJPARepository;
    private final DocumentElasticsearchRepository documentElasticsearchRepository;
    private final DocumentContentJPARepository documentContentJPARepository;
    private final RabbitMQSenderService rabbitMQSenderService;
    private final MinioFileUploader minioFileUploader;

    public DocumentService(DocumentJPARepository documentJPARepository, DocumentElasticsearchRepository documentElasticsearchRepository,
                           DocumentContentJPARepository documentContentJPARepository, RabbitMQSenderService rabbitMQSenderService, MinioFileUploader minioFileUploader) {
        this.documentJPARepository = documentJPARepository;
        this.documentElasticsearchRepository = documentElasticsearchRepository;
        this.documentContentJPARepository = documentContentJPARepository;

        this.rabbitMQSenderService = rabbitMQSenderService;
        this.minioFileUploader = minioFileUploader;
    }
    public List<DocumentDto> getDocuments() {
        return new ArrayList<>(documentJPARepository.findAll()
        );
    }

    public DocumentDto getDocumentById(UUID documentId) {
        return documentJPARepository.findById(documentId).orElse(null);
    }

    public DocumentDto createDocument(DocumentDto documentDto, MultipartFile file) {
        if(file == null) {
            System.out.println("No file provided");
            return null;
        }
       DocumentDto createdDocumentDto = documentJPARepository.save(documentDto);
        minioFileUploader.upload(file, createdDocumentDto.getId());
       rabbitMQSenderService.sendToOcrQueue(createdDocumentDto.getId().toString());
       return createdDocumentDto;
    }
    public Void deleteDocumentById(UUID documentId) {
        documentJPARepository.deleteById(documentId);
        documentContentJPARepository.deleteById(documentId);
        return null;
    }

    public DocumentContentDto getDocumentContent(UUID documentId) {
        return documentContentJPARepository.findById(documentId).orElseThrow(
                () -> new RuntimeException("Document content not found for ID: " + documentId)
        );
    }

    public DocumentDto updateFile(DocumentDto documentDto) {
        return documentJPARepository.save(documentDto);
    }

    public List<DocumentDto> searchDocumentContent(String search) {
        // Fetch data from Elasticsearch repository
        List<DocumentContentDto> documentContentDtoList = documentElasticsearchRepository.findByContentContaining(search);

        // Return an empty list if the result is null or empty
        if (documentContentDtoList == null || documentContentDtoList.isEmpty()) {
            return new ArrayList<>(); // Return empty list instead of null
        }

        // Map the Elasticsearch results to DocumentDto
        List<DocumentDto> documentDtoList = new ArrayList<>();
        for (DocumentContentDto documentContentDto : documentContentDtoList) {
            documentJPARepository.findById(documentContentDto.getId())
                    .ifPresent(documentDtoList::add);
        }

        return documentDtoList;
    }

}
