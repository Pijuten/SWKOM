package org.openapitools.services.dto;

import lombok.Data;
import org.springframework.core.io.Resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "documents")
public class DocumentDto {
    @Id
    private UUID id;
    private String title;
    private String username;
    private String description;
    private String uploadedDate;
}
