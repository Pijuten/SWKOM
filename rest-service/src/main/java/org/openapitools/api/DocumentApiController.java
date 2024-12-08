package org.openapitools.api;

import org.openapitools.model.Document;
import java.util.UUID;


import org.openapitools.services.dto.DocumentDto;
import org.openapitools.services.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-07T17:55:25.345714006+01:00[Europe/Vienna]", comments = "Generator version: 7.10.0")
@Controller
@RequestMapping("${openapi.example.base-path:}")
public class DocumentApiController implements DocumentApi {

    private final DocumentService documentService;
    private final NativeWebRequest request;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentApiController(DocumentService documentService, NativeWebRequest request, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.request = request;
        this.documentMapper = documentMapper;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> documentDelete(UUID id) {
        return ResponseEntity.ok(documentService.deleteDocumentById(id));
    }

    @Override
    public ResponseEntity<List<Document>> documentGet() {
        List<DocumentDto> documentDtos = documentService.getDocuments();
        List<Document> documents = documentDtos.stream()
                .map(documentMapper::dtoToEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(documents);
    }

    @Override
    public ResponseEntity<Document> documentIdGet(UUID id) {
        DocumentDto documentDto = documentService.getDocumentById(id);
        return ResponseEntity.ok(documentMapper.dtoToEntity(documentDto));
    }

    @Override
    public ResponseEntity<Document> documentPost(String title, String username, String description, MultipartFile file, UUID id) {
        Document document = new Document(title,username,description,file);
        DocumentDto createdDto = documentService.createDocument(DocumentMapper.INSTANCE.entityToDto(document), file);
        return ResponseEntity.ok(DocumentMapper.INSTANCE.dtoToEntity(createdDto));
    }

    @Override
    public ResponseEntity<Document> documentPut(String title, String username, String description, MultipartFile file, UUID id) {
        Document document = new Document(title,username,description,file);
        document.setId(id);
        DocumentDto documentDto = documentMapper.entityToDto(document);
        return ResponseEntity.ok(documentMapper.dtoToEntity(documentService.updateFile(documentDto)));
    }

}
