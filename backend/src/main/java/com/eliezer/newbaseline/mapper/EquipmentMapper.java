package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EquipmentRequestDTO;
import com.eliezer.newbaseline.dto.response.EquipmentResponseDTO;
import com.eliezer.newbaseline.model.Equipment;
import com.eliezer.newbaseline.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EquipmentMapper {
    @Mapping(source = "createdById", target = "createdBy")
    Equipment toEntity(EquipmentRequestDTO dto);

    @Mapping(source = "createdBy", target = "createdBy.fullName")
    EquipmentResponseDTO toDTO(Equipment entity);

    default User toUserFromId(Long createdById) {
        if (createdById == null) {
            return null;
        }
        User user = new User();
        user.setId(createdById);
        return user;
    }

    default String toFullNameFromUser(User createdBy) {
        return createdBy != null ? createdBy.getFullName() : null;
    }
}
