package org.openapitools.services.mapper;

import javax.annotation.processing.Generated;
import org.openapitools.model.Document;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T04:04:07+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentDto entityToDto(Document entity) {
        if ( entity == null ) {
            return null;
        }

        DocumentDto documentDto = new DocumentDto();

        documentDto.setId( entity.getId() );
        documentDto.setUsername( entity.getUsername() );
        documentDto.setDescription( entity.getDescription() );
        documentDto.setUploadedDate( entity.getUploadedDate() );

        return documentDto;
    }

    @Override
    public Document dtoToEntity(DocumentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( dto.getId() );
        document.setUsername( dto.getUsername() );
        document.setDescription( dto.getDescription() );
        document.setUploadedDate( dto.getUploadedDate() );

        return document;
    }
}
