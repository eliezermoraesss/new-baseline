package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineItemRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineItemResponseDTO;
import com.eliezer.newbaseline.model.BaselineItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EntryMapper.class, BaselineMapper.class})
public interface BaselineItemMapper {
    BaselineItem toEntity(BaselineItemRequestDTO dto);

    @Mapping(source = "entry.id", target = "entryId")
    BaselineItemResponseDTO toDTO(BaselineItem entity);
}
