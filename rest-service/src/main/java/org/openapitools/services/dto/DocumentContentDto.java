package org.openapitools.services.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DocumentContentDto {
    private UUID documentId;
    private String content;
}
