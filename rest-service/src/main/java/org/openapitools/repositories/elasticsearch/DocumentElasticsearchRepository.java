package org.openapitools.repositories.elasticsearch;

import org.openapitools.services.dto.DocumentContentDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentElasticsearchRepository extends ElasticsearchRepository<DocumentContentDto, UUID> {

    List<DocumentContentDto> findByContent(String search);
}
