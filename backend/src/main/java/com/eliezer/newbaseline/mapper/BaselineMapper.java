package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface BaselineMapper {
    // Entrada - DTO->Entity (Request)
    @Mapping(target = "createdBy", source = "createdById")
    Baseline toEntity(BaselineRequestDTO dto);

    // Saída Entity->DTO (Response)
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "entries", target = "entries")
    @Mapping(source = "notifications", target = "notifications")
    @Mapping(source = "logEvents", target = "logEvents")
    BaselineResponseDTO toResponseDTO(Baseline entity);

}
