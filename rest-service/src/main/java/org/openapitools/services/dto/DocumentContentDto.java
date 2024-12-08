package org.openapitools.services.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "document_contents")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "document")
public class DocumentContentDto {
    @Id
    private UUID id;
    @Lob
    private String content;

    public DocumentContentDto(UUID id, String content) {
        this.id = id;
        this.content = content;
    }

    public DocumentContentDto() {

    }
}
