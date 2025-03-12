package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.BaselineItemDTO;
import com.eliezer.newbaseline.model.BaselineItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaselineItemMapper {
    BaselineItem toEntity(BaselineItemDTO dto);
    BaselineItemDTO toDTO(BaselineItem entity);
}
