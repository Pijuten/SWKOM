package org.openapitools.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.Document;
import org.openapitools.services.dto.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface DocumentMapper extends BaseMapper<Document, DocumentDto> {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Override
    DocumentDto entityToDto(Document entity);

    @Override
    @Mapping(target = "file", ignore = true)
    Document dtoToEntity(DocumentDto dto);
}