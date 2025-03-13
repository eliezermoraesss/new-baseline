package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EquipmentRequestDTO;
import com.eliezer.newbaseline.dto.response.EquipmentResponseDTO;
import com.eliezer.newbaseline.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EquipmentMapper {
    @Mapping(source = "createdById", target = "createdBy")
    Equipment toEntity(EquipmentRequestDTO dto);

    @Mapping(source = "createdBy.fullName", target = "createdBy")
    EquipmentResponseDTO toDTO(Equipment entity);
}
