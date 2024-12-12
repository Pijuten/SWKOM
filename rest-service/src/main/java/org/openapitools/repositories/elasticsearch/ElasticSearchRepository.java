package org.openapitools.repositories.elasticsearch;

import org.openapitools.services.dto.DocumentContentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ElasticSearchRepository extends JpaRepository<DocumentContentDto, UUID> {
}
