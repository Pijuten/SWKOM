package at.fhtw.ocrservice.services.mapper;

import at.fhtw.ocrservice.model.DocumentContent;
import at.fhtw.ocrservice.services.dto.DocumentContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface DocumentContentMapper extends BaseMapper<DocumentContent, DocumentContentDto> {
    DocumentContentMapper INSTANCE = Mappers.getMapper(DocumentContentMapper.class);

    @Override
    DocumentContentDto entityToDto(DocumentContent entity);

    @Override
    DocumentContent dtoToEntity(DocumentContentDto dto);
}