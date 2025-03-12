package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.LogEventDTO;
import com.eliezer.newbaseline.model.LogEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogEventMapper {
    LogEvent toEntity(LogEventDTO dto);
    LogEventDTO toDTO(LogEvent entity);
}
