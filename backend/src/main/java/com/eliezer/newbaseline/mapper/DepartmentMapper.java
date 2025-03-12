package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.DepartmentDTO;
import com.eliezer.newbaseline.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentDTO dto);
    DepartmentDTO toDTO(Department entity);
}
