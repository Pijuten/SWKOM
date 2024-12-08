package at.fhtw.ocrservice.repositories.jpa;



import at.fhtw.ocrservice.services.dto.DocumentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentDto, UUID> {

}
