package at.fhtw.ocrservice.repositories.jpa;


import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContentDto, UUID> {
}
