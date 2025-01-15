package org.openapitools.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final MinioService minioService;
    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    public DocumentService(DocumentJPARepository documentJPARepository, DocumentElasticsearchRepository documentElasticsearchRepository,
                           DocumentContentJPARepository documentContentJPARepository, RabbitMQSenderService rabbitMQSenderService, MinioService minioService) {
        this.documentJPARepository = documentJPARepository;
        this.documentElasticsearchRepository = documentElasticsearchRepository;
        this.documentContentJPARepository = documentContentJPARepository;

        this.rabbitMQSenderService = rabbitMQSenderService;
        this.minioService = minioService;
    }

    public List<DocumentDto> getDocuments() {
        try {
            return new ArrayList<>(documentJPARepository.findAll());
        }
        catch (Exception e) {
            logger.error("Error fetching all documents", e);
            throw new RuntimeException("Failed to fetch all documents", e);
        }
    }

    public DocumentDto getDocumentById(UUID documentId) {
       try {
           return documentJPARepository.findById(documentId).orElse(null);
       }
       catch(Exception e) {
           logger.error("Error fetching document with ID: {}", documentId, e);
           throw new RuntimeException("Failed to fetch document", e);
       }
    }

    public DocumentDto createDocument(DocumentDto documentDto, MultipartFile file) {
        try {
            if (file == null) {
                logger.warn("No file provided for document creation");
                throw new IllegalArgumentException("No file provided for document creation");
            }

            DocumentDto createdDocumentDto = documentJPARepository.save(documentDto);
            minioService.upload(file, createdDocumentDto.getId());
            rabbitMQSenderService.sendToOcrQueue(createdDocumentDto.getId().toString());

            logger.info("Document created successfully: {}", documentDto.getId());
            return createdDocumentDto;

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException("Document creation failed", e);
        }

    }

    public Void deleteDocumentById(UUID documentId) {
        logger.info("Deleting document with ID: {}", documentId);

        try {
            documentJPARepository.deleteById(documentId);
            documentContentJPARepository.deleteById(documentId);
            minioService.deleteFile(documentId.toString());

            logger.info("Document deleted successfully: {}", documentId);
        }catch(Exception e){
            logger.error("Failed to delete document with ID: {}",documentId, e);
            throw new IllegalArgumentException("Failed to delete document", e);
        }
        return null;
    }

    public DocumentContentDto getDocumentContent(UUID documentId) {
       try{
        return documentContentJPARepository.findById(documentId).orElseThrow(
                () -> new RuntimeException("Document content not found for ID: " + documentId)
        );
    }
       catch (Exception e){
       logger.error("Error fetching document content", e);
       throw e;
       }
    }

    public DocumentDto updateFile(DocumentDto documentDto) {
       try{
           return documentJPARepository.save(documentDto);
       }
       catch (Exception e){
           logger.error("Error updating document content", e);
           throw new RuntimeException("Error updating document content", e);
       }
    }

    public List<DocumentDto> searchDocumentContent(String search) {
       try{
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
       catch (Exception e){
       logger.error("Error searching document", e);
       throw new RuntimeException("Error searching document", e);
       }
    }

}
