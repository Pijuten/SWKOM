package org.openapitools.services.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "documents")
public class DocumentDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String title;
    private String username;
    private String description;
    private String uploadedDate;
}