package org.openapitools.api;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentContentRepository documentContentRepository;

    public DocumentService(DocumentRepository documentRepository,
                           DocumentContentRepository documentContentRepository) {
        this.documentRepository = documentRepository;
        this.documentContentRepository = documentContentRepository;
        log.info("DocumentService initialized");
    }

    public List<DocumentDto> getDocuments() {
        log.info("Fetching all documents");
        try {
            List<DocumentDto> documents = new ArrayList<>(documentRepository.findAll());
            log.info("Successfully retrieved {} documents", documents.size());
            return documents;
        } catch (Exception e) {
            log.error("Error fetching all documents: {}", e.getMessage(), e);
            throw e;
        }
    }

    public DocumentDto getDocumentById(UUID documentId) {
        log.info("Fetching document with ID: {}", documentId);
        try {
            DocumentDto document = documentRepository.findById(documentId).orElse(null);
            if (document == null) {
                log.error("Document not found with ID: {}", documentId);
            } else {
                log.info("Successfully retrieved document with ID: {}", documentId);
            }
            return document;
        } catch (Exception e) {
            log.error("Error fetching document with ID {}: {}", documentId, e.getMessage(), e);
            throw e;
        }
    }

    public DocumentDto createDocument(DocumentDto documentDto) {
        log.info("Creating new document");
        try {
            DocumentDto savedDocument = documentRepository.save(documentDto);
            log.info("Successfully created document with ID: {}", savedDocument.getId());
            return savedDocument;
        } catch (Exception e) {
            log.error("Error creating document: {}", e.getMessage(), e);
            throw e;
        }
    }

    public Void deleteDocumentById(UUID documentId) {
        log.info("Deleting document with ID: {}", documentId);
        try {
            documentRepository.deleteById(documentId);
            log.info("Successfully deleted document with ID: {}", documentId);
            return null;
        } catch (Exception e) {
            log.error("Error deleting document with ID {}: {}", documentId, e.getMessage(), e);
            throw e;
        }
    }

    public DocumentContentDto getDocumentContent(UUID documentId) {
        log.info("Fetching content for document with ID: {}", documentId);
        try {
            DocumentContentDto content = documentContentRepository.findById(documentId).orElse(null);
            if (content == null) {
                log.error("Document content not found for ID: {}", documentId);
            } else {
                log.info("Successfully retrieved content for document with ID: {}", documentId);
            }
            return content;
        } catch (Exception e) {
            log.error("Error fetching document content for ID {}: {}", documentId, e.getMessage(), e);
            throw e;
        }
    }

    public DocumentContentDto saveDocumentContent(DocumentContentDto documentContentDto) {
        log.info("Saving document content");
        try {
            DocumentContentDto savedContent = documentContentRepository.save(documentContentDto);
            log.info("Successfully saved content for document with ID: {}", savedContent.getDocumentId());
            return savedContent;
        } catch (Exception e) {
            log.error("Error saving document content: {}", e.getMessage(), e);
            throw e;
        }
    }
}