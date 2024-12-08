package org.openapitools.api;

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
        return DocumentcontentApi.super.documentcontentDelete(id);
    }

    @Override
    public ResponseEntity<List<Document>> documentcontentGet(String search) {
        List<DocumentDto> documentDtos = documentService.searchDocumentContent(search);
        List<Document> documents = documentDtos.stream()
                .map(documentMapper::dtoToEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(documents);
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentIdGet(UUID id) {
        return ResponseEntity.ok(documentContentMapper.dtoToEntity(documentService.getDocumentContent(id)));
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentPost(DocumentContent documentContent) {
        return DocumentcontentApi.super.documentcontentPost(documentContent);
    }

    @Override
    public ResponseEntity<DocumentContent> documentcontentPut(DocumentContent documentContent) {
        return DocumentcontentApi.super.documentcontentPut(documentContent);
    }

}
