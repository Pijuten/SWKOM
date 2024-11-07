package org.openapitools.services.mapper;

import javax.annotation.processing.Generated;
import org.openapitools.model.DocumentContent;
import org.openapitools.services.dto.DocumentContentDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-07T04:04:07+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class DocumentContentMapperImpl implements DocumentContentMapper {

    @Override
    public DocumentContentDto entityToDto(DocumentContent entity) {
        if ( entity == null ) {
            return null;
        }

        DocumentContentDto documentContentDto = new DocumentContentDto();

        documentContentDto.setId( entity.getId() );
        documentContentDto.setContent( entity.getContent() );

        return documentContentDto;
    }

    @Override
    public DocumentContent dtoToEntity(DocumentContentDto dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentContent documentContent = new DocumentContent();

        documentContent.setId( dto.getId() );
        documentContent.setContent( dto.getContent() );

        return documentContent;
    }
}
