package org.openapitools.api;

import org.openapitools.services.RabbitMQSenderService;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentContentRepository documentContentRepository;
    private final RabbitMQSenderService rabbitMQSenderService;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentContentRepository documentContentRepository, RabbitMQSenderService rabbitMQSenderService) {
        this.documentRepository = documentRepository;
        this.documentContentRepository = documentContentRepository;

        this.rabbitMQSenderService = rabbitMQSenderService;
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

    public DocumentContentDto getDocumentContent(UUID documentId) {
        return documentContentRepository.findById(documentId).orElse(null);
    }
    public DocumentContentDto saveDocumentContent(DocumentContentDto documentContentDto){
        return documentContentRepository.save(documentContentDto);
    }
    @RabbitListener(queues = "result_queue")
    public String receiveDocumentCreated (String message) {
        return message;
    }
}
