package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.EntryDTO;
import com.eliezer.newbaseline.model.Entry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntryMapper {
    Entry toEntity(EntryDTO dto);
    EntryDTO toDTO(Entry entity);
}
