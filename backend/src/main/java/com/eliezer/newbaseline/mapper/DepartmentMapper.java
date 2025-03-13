package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.DepartmentRequestDTO;
import com.eliezer.newbaseline.dto.response.DepartmentResponseDTO;
import com.eliezer.newbaseline.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toEntity(DepartmentRequestDTO dto);
    DepartmentResponseDTO toDTO(Department entity);
}
