package org.openapitools.api;

import org.openapitools.model.DocumentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentContentRepository extends JpaRepository<DocumentContent, UUID> {
}
