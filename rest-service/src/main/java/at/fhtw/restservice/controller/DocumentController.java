package at.fhtw.restservice.controller;

import at.fhtw.restservice.model.Document;
import at.fhtw.restservice.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

@RestController
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createDocument(@RequestPart("document") String documentStr, @RequestPart("file") MultipartFile file) {
        documentService.createDocument(file);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<String>  updateDocument(@RequestBody Document document) {
        documentService.updateDocument(document);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/delete/{id}")
    public  ResponseEntity<String> deleteDocument(@RequestParam String id) {
        documentService.deleteDocument(id);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public  ResponseEntity<String> getAllDocuments() {
        documentService.getAllDocuments();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public  ResponseEntity<String> getDocument(@RequestParam String id) {
        documentService.getDocument(id);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
    @PostMapping("/search")
    public  ResponseEntity<String> searchDocument(@RequestParam String query) {
        documentService.searchDocument(query);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
