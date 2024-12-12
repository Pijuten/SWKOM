package at.fhtw.ocrservice.repositories.elasticsearch;

import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ElasticSearchRepository extends JpaRepository<DocumentContentDto, UUID> {
}
