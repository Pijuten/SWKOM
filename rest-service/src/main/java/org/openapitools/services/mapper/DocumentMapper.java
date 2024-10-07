package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.Document;
import org.openapitools.services.dto.DocumentDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface DocumentMapper extends BaseMapper<Document, DocumentDto> {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Override
    DocumentDto entityToDto(Document entity);

    @Override
    Document dtoToEntity(DocumentDto dto);
}