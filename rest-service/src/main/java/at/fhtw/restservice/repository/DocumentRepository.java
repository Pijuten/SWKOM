package at.fhtw.restservice.repository;

import at.fhtw.restservice.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {
    public void save(Document document) {
        return;
    }
    public Document findById(Long id) {
        return new Document();
    }
    public void delete(Long id) {
        return;
    }
    public void update(Document document) {
        return;
    }

    public void getAll() {
    }

    public void get(long l) {
    }

    public void search(String query) {
    }
}
