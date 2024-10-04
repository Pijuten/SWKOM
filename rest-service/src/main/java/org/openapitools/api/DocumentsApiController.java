package org.openapitools.api;

import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-09-23T21:54:24.569617300+02:00[Europe/Berlin]", comments = "Generator version: 7.8.0")
@Controller
@RequestMapping("${openapi.sWKOM.base-path:}")
public class DocumentsApiController implements DocumentsApi {
    private final DocumentContentRepository documentContentRepository;

    @Autowired
    public DocumentsApiController(DocumentService documentService, NativeWebRequest request, DocumentContentRepository documentContentRepository) {
        this.documentService = documentService;
        this.request = request;
        this.documentContentRepository = documentContentRepository;
    }

    private final DocumentService documentService;

    private final NativeWebRequest request;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    //Create Document
    @Override
    public ResponseEntity<Document> documentsPost(Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }

    //Read Documents
    @Override
    public ResponseEntity<Document> documentsDocumentIdGet(UUID documentId) {
        return ResponseEntity.ok(documentService.getDocumentById(documentId));
    }
    @Override
    public ResponseEntity<List<Document>> documentsGet() {
        return ResponseEntity.ok(documentService.getDocuments());
    }

    //Update Documents
    @Override
    public ResponseEntity<Document> documentsPut(Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }

    //Delete Documents
    @Override
    public ResponseEntity documentsDocumentIdDelete(UUID documentId) {
        return ResponseEntity.ok(documentService.deleteDocumentById(documentId));
    }

    //Create/Update DocumentContent
    @Override
    public ResponseEntity<DocumentContent> documentsContentPut(DocumentContent documentContent) {
        return ResponseEntity.ok(documentService.saveDocumentContent(documentContent));
    }

    //Read DocumentContent
    @Override
    public ResponseEntity<DocumentContent> documentsDocumentIdContentGet(UUID documentId) {
        return ResponseEntity.ok(documentService.getDocumentContent(documentId));
    }

}