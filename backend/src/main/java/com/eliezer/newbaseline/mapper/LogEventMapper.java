package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.LogEventRequestDTO;
import com.eliezer.newbaseline.dto.response.LogEventResponseDTO;
import com.eliezer.newbaseline.model.LogEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BaselineMapper.class, EntryMapper.class})
public interface LogEventMapper {
    LogEvent toEntity(LogEventRequestDTO dto);

    @Mapping(target = "createdBy", source = "createdBy.fullName")
    @Mapping(target = "baselineCode", source = "baseline.baselineCode")
    @Mapping(target = "entryId", source = "entry.id")
    LogEventResponseDTO toDTO(LogEvent entity);
}
