package org.openapitools.services.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DocumentDto {
    private UUID id;
    private String title;
    private String username;
    private String description;
    private String uploadedDate;
}
