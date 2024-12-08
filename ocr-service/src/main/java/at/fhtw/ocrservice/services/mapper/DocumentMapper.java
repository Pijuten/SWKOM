package at.fhtw.ocrservice.services.mapper;

import at.fhtw.ocrservice.model.Document;
import at.fhtw.ocrservice.services.dto.DocumentDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface DocumentMapper extends BaseMapper<Document, DocumentDto> {
    DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

    @Override
    DocumentDto entityToDto(Document entity);

    @Override
    Document dtoToEntity(DocumentDto dto);
}