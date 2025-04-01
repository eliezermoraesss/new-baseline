package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.RoleDTO;
import com.eliezer.newbaseline.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "authority", source = "authority")
    Role toEntity(RoleDTO dto);

    @Mapping(target = "authority", source = "authority")
    RoleDTO toDTO(Role entity);

    @Mapping(target = "id", ignore = true)
    void updateRoleFromDTO(RoleDTO dto, @MappingTarget Role entity);
}
