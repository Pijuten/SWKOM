package org.openapitools.api;

import org.openapitools.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    public final List<Document> Documents = new ArrayList<>(
            List.of(new Document[]{
                    new Document()
                            .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                            .description("description")
                            .username("username")
                            .uploadedDate("2023-06-13"),
                    new Document()
                            .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                            .description("description")
                            .username("username")
                            .uploadedDate("2023-06-13")
            })
    );

    public List<Document> getDocuments() {
        return Documents;
    }

    public Void deleteDocumentById(UUID documentId) {
        Documents.removeIf(document -> document.getId().equals(documentId));
        return null;
    }

    public Document getDocumentById(UUID documentId) {
        return Documents.stream()
                .filter(document -> document.getId().equals(documentId))
                .findFirst()
                .orElse(null);
    }

    public Document createDocument(Document document) {
        Documents.add(document);
        return document;
    }
}
