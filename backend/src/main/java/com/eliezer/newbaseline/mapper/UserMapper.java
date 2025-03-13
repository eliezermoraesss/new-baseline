package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.UserDTO;
import com.eliezer.newbaseline.model.Department;
import com.eliezer.newbaseline.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, RoleMapper.class})
public interface UserMapper {
    @Mapping(source = "departmentId", target = "department")
    User toEntity(UserDTO dto);

    @Mapping(source = "department", target = "departmentId")
    UserDTO toDTO(User entity);

    default Long map(Department department) {
        return department != null ? department.getId() : null;
    }

    default Department map(Long departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        return department;
    }
}
