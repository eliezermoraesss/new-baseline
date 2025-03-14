package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineItemRequestDTO;
import com.eliezer.newbaseline.model.BaselineItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaselineItemMapper {
    BaselineItem toEntity(BaselineItemRequestDTO dto);
    BaselineItemRequestDTO toDTO(BaselineItem entity);
}
