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
    public final List<DocumentContent> documentContents = new ArrayList<>(
            List.of(
                    new DocumentContent[]{
                            new DocumentContent()
                                    .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                                    .content("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."),
                            new DocumentContent()
                                    .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                                    .content("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")

                    }
            )
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
        document.setId(UUID.randomUUID());
        Documents.add(document);
        return document;
    }

    public Document putDocumentContent(UUID documentId, Document document) {
        Documents.removeIf(filteredDocument ->filteredDocument.getId().equals(documentId));
        document.setId(documentId);
        Documents.add(document);
        return document;
    }

    public DocumentContent getDocumentContent(UUID documentId) {
        return documentContents.stream()
                .filter(content -> content.getId().equals(documentId))
                .findFirst()
                .orElse(null);
    }
}
