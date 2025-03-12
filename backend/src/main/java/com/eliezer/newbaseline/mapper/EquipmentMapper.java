package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EquipmentRequestDTO;
import com.eliezer.newbaseline.model.Equipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment toEntity(EquipmentRequestDTO dto);
    EquipmentRequestDTO toDTO(Equipment entity);
}
