package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EntryRequestDTO;
import com.eliezer.newbaseline.model.Entry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    Entry toEntity(EntryRequestDTO dto);
    EntryRequestDTO toDTO(Entry entity);
}
