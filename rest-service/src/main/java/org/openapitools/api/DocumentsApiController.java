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
    @Autowired
    public DocumentsApiController(DocumentService documentService, NativeWebRequest request) {
        this.documentService = documentService;
        this.request = request;
    }

    private final DocumentService documentService;

    private final NativeWebRequest request;


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<DocumentContent> documentsDocumentIdContentGet(UUID documentId) {
        return ResponseEntity.ok(documentService.getDocumentContent(documentId));
    }

    @Override
    public ResponseEntity<Document> documentsDocumentIdContentPut(UUID documentId, Document document) {
        return ResponseEntity.ok(documentService.putDocumentContent(documentId,document));
    }

    @Override
    public ResponseEntity documentsDocumentIdDelete(UUID documentId) {
        return ResponseEntity.ok(documentService.deleteDocumentById(documentId));
    }

    @Override
    public ResponseEntity<Document> documentsDocumentIdGet(UUID documentId) {
        return ResponseEntity.ok(documentService.getDocumentById(documentId));
    }

    @Override
    public ResponseEntity<List<Document>> documentsGet() {
        return ResponseEntity.ok(documentService.getDocuments());
    }

    @Override
    public ResponseEntity<Document> documentsPost(Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }
}
