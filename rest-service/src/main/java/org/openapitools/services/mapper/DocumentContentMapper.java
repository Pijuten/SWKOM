package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.Document;
import org.openapitools.model.DocumentContent;
import org.openapitools.services.dto.DocumentContentDto;
import org.openapitools.services.dto.DocumentDto;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface DocumentContentMapper extends BaseMapper<DocumentContent, DocumentContentDto> {
    DocumentContentMapper INSTANCE = Mappers.getMapper(DocumentContentMapper.class);

    @Override
    DocumentContentDto entityToDto(DocumentContent entity);

    @Override
    DocumentContent dtoToEntity(DocumentContentDto dto);
}