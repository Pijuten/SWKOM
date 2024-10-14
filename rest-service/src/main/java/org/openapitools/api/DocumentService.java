package org.openapitools.api;

import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.openapitools.services.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentContentRepository documentContentRepository;
    private final DocumentMapper documentMapper;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentContentRepository documentContentRepository,
                           DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentContentRepository = documentContentRepository;
        this.documentMapper = documentMapper;
    }
    public List<DocumentDto> getDocuments() {
        return new ArrayList<>(documentRepository.findAll()
        );
    }

    public DocumentDto getDocumentById(UUID documentId) {
        return documentRepository.findById(documentId).orElse(null);
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        return documentRepository.save(documentDto);
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

}
