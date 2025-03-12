package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.BaselineDTO;
import com.eliezer.newbaseline.model.Baseline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaselineMapper {
    Baseline toEntity(BaselineDTO dto);
    BaselineDTO toDTO(Baseline entity);
}
