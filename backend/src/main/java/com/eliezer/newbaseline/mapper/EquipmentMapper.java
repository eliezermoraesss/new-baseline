package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.EquipmentDTO;
import com.eliezer.newbaseline.model.Equipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    Equipment toEntity(EquipmentDTO dto);
    EquipmentDTO toDTO(Equipment entity);
}
