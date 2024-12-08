package at.fhtw.ocrservice.services.mapper;

import org.openapitools.jackson.nullable.JsonNullable;

public interface BaseMapper<EN, DTO> {
    DTO entityToDto(EN entity);

    EN dtoToEntity(DTO dto);

    default String map(JsonNullable<String> value) {
        return value!=null && value.isPresent() ? value.get() : null;
    }

    default JsonNullable<String> map(String value) {
        return JsonNullable.of(value);
    }
}
