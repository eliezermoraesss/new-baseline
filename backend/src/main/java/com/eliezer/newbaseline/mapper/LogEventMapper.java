package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.LogEventRequestDTO;
import com.eliezer.newbaseline.model.LogEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogEventMapper {
    LogEvent toEntity(LogEventRequestDTO dto);
    LogEventRequestDTO toDTO(LogEvent entity);
}
