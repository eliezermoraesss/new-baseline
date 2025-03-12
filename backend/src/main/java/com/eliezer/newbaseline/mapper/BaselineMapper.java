package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaselineMapper {
    //Entrada -> Entity
    Baseline toEntity(BaselineRequestDTO dto);

    //Entity -> Saída
    BaselineResponseDTO toResponseDTO(Baseline entity);
}
