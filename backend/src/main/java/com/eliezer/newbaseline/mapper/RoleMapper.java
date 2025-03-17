package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.RoleDTO;
import com.eliezer.newbaseline.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toEntity(RoleDTO dto);
    RoleDTO toDTO(Role entity);

    void updateRoleFromDTO(RoleDTO roleDTO, Role role);
}
