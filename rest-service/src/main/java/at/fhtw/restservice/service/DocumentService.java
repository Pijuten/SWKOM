package at.fhtw.restservice.service;

import at.fhtw.restservice.model.Document;
import at.fhtw.restservice.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void createDocument(MultipartFile file) {
        documentRepository.save(new Document());
    }

    public void updateDocument(Document document) {
        documentRepository.update(document);
    }

    public void deleteDocument(String id) {
        documentRepository.delete(Long.parseLong(id));
    }

    public void getAllDocuments() {
        documentRepository.getAll();
    }

    public void getDocument(String id) {
        documentRepository.get(Long.parseLong(id));
    }

    public void searchDocument(String query) {
        documentRepository.search(query);
    }
}
