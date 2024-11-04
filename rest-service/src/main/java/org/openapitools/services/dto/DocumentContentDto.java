package org.openapitools.services.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "document_contents")
public class DocumentContentDto {
    @Id
    private UUID id;
    private String content;
}
