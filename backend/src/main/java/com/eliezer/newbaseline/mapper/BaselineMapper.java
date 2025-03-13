package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EntryMapper.class})
public interface BaselineMapper {
    @Mapping(target = "createdBy", source = "createdById")
    Baseline toEntity(BaselineRequestDTO dto);

    BaselineResponseDTO toDTO(Baseline entity);
}
