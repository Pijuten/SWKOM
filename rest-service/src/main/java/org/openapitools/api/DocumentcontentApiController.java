package org.openapitools.api;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;

import java.util.*;
import java.util.stream.Collectors;


import org.openapitools.services.dto.DocumentDto;
import org.openapitools.services.mapper.DocumentContentMapper;
import org.openapitools.services.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;


import javax.annotation.Generated;
@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-07T17:55:25.345714006+01:00[Europe/Vienna]", comments = "Generator version: 7.10.0")
@Controller
@RequestMapping("${openapi.example.base-path:}")
public class DocumentcontentApiController implements DocumentcontentApi {

    private final NativeWebRequest request;
    private final DocumentContentMapper documentContentMapper;
    private final DocumentMapper documentMapper;
    private final DocumentService documentService;

    @Autowired
    public DocumentcontentApiController(NativeWebRequest request, DocumentContentMapper documentContentMapper, DocumentMapper documentMapper, DocumentService documentService) {
        this.request = request;
        this.documentContentMapper = documentContentMapper;
        this.documentMapper = documentMapper;
        this.documentService = documentService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> documentcontentDelete(UUID id) {
        try {
            log.info("Document content deleted successfully with id: {}", id);
            return DocumentcontentApi.super.documentcontentDelete(id);
        } catch (Exception e) {
            log.error("Error deleting document content with ID: {}", id, e.getMessage());
            //return internal service error
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<List<Document>> documentcontentGet(String search) {
        try {
            List<DocumentDto> documentDtos = documentService.searchDocumentContent(search);
            List<Document> documents = documentDtos.stream()
                    .map(documentMapper::dtoToEntity)
                    .collect(Collectors.toList());
            log.info("Found {} documents for search term: {}", documentDtos.size(), search);
            return ResponseEntity.ok(documents);

        } catch (Exception e) {
            log.info("Failed fetching documents with search term: {}", search, e);
            return ResponseEntity.status(500).build(); // internal service error
        }
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentIdGet(UUID id) {
        try {
            log.info("Successfully retrieved Document content with id: {}", id);
            return ResponseEntity.ok(documentContentMapper.dtoToEntity(documentService.getDocumentContent(id)));
        } catch (Exception e) {
            log.info("Failed retrieving document content with ID: {}", id, e);
        }
        return ResponseEntity.status(404).build(); // not found
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentPost(DocumentContent documentContent) {
        try {
            return DocumentcontentApi.super.documentcontentPost(documentContent);
        } catch (Exception e) {
            log.info("Failed posting document content with ID: {}", documentContent.getId(), e);
            return ResponseEntity.status(500).build(); // internal service error
        }
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentPut(DocumentContent documentContent) {
        try {
            log.info("Successfully updated Document content with id: {}", documentContent.getId());
            return DocumentcontentApi.super.documentcontentPut(documentContent);
        } catch (Exception e) {
            log.info("Failed updating document content with ID: {}", documentContent.getId(), e);
            return ResponseEntity.status(500).build();
        }
        }

}