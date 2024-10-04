package org.openapitools.api;

import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    public DocumentService(DocumentRepository documentRepository, DocumentContentRepository documentContentRepository) {
        this.documentRepository = documentRepository;
        this.documentContentRepository = documentContentRepository;
    }
    private final DocumentRepository documentRepository;
    private final DocumentContentRepository documentContentRepository;


    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Void deleteDocumentById(UUID documentId) {
        documentRepository.deleteById(documentId);
        return null;
    }

    public Document getDocumentById(UUID documentId) {
        return documentRepository.findById(documentId).orElse(null);
    }

    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    public DocumentContent getDocumentContent(UUID documentId) {
        return documentContentRepository.findById(documentId).orElse(null);
    }
    public DocumentContent saveDocumentContent(DocumentContent documentContent){
        return documentContentRepository.save(documentContent);
    }

}
