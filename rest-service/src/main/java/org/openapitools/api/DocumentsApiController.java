package org.openapitools.api;

import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;

import java.util.*;
import java.util.stream.Collectors;


import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;
import org.openapitools.services.mapper.DocumentContentMapper;
import org.openapitools.services.mapper.DocumentMapper;
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
    private final DocumentService documentService;
    private final NativeWebRequest request;
    private final DocumentMapper documentMapper;
    private final DocumentContentMapper documentContentMapper;

    @Autowired
    public DocumentsApiController(DocumentService documentService,
                                  NativeWebRequest request,
                                  DocumentContentRepository documentContentRepository, DocumentContentRepository documentContentRepository1,
                                  DocumentMapper documentMapper, DocumentContentMapper documentContentMapper) {
        this.documentService = documentService;
        this.request = request;
        this.documentContentRepository = documentContentRepository1;
        this.documentMapper = documentMapper;
        this.documentContentMapper = documentContentMapper;
    }

    @Override
    public ResponseEntity<Document> documentsPost(Document document) {
        DocumentDto documentDto =  DocumentMapper.INSTANCE.entityToDto(document);
        DocumentDto createdDto = documentService.createDocument(documentDto);
        return ResponseEntity.ok(DocumentMapper.INSTANCE.dtoToEntity(createdDto));
    }

    @Override
    public ResponseEntity<Document> documentsDocumentIdGet(UUID documentId) {
        DocumentDto documentDto = documentService.getDocumentById(documentId);
        return ResponseEntity.ok(documentMapper.dtoToEntity(documentDto));
    }

    @Override
    public ResponseEntity<List<Document>> documentsGet() {
        List<DocumentDto> documentDtos = documentService.getDocuments();
        List<Document> documents = documentDtos.stream()
                .map(documentMapper::dtoToEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(documents);
    }

    //Update Documents
    @Override
    public ResponseEntity<Document> documentsPut(Document document) {
        DocumentDto documentDto = documentMapper.entityToDto(document);
        return ResponseEntity.ok(documentMapper.dtoToEntity(documentService.createDocument(documentDto)));
    }

    //Delete Documents
    @Override
    public ResponseEntity documentsDocumentIdDelete(UUID documentId) {
        return ResponseEntity.ok(documentService.deleteDocumentById(documentId));
    }

    //Create/Update DocumentContent
    @Override
    public ResponseEntity<DocumentContent> documentsContentPut(DocumentContent documentContent) {
        DocumentContentDto documentContentDto = documentContentMapper.entityToDto(documentContent);
        return ResponseEntity.ok(documentContentMapper.dtoToEntity(documentService.saveDocumentContent(documentContentDto)));
    }

    //Read DocumentContent
    @Override
    public ResponseEntity<DocumentContent> documentsDocumentIdContentGet(UUID documentId) {
        return ResponseEntity.ok(documentContentMapper.dtoToEntity(documentService.getDocumentContent(documentId)));
    }

}