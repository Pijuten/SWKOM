package at.fhtw.ocrservice.repositories.elasticsearch;

import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentElasticsearchRepository extends ElasticsearchRepository<DocumentContentDto, String> {
}
