package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.UserDTO;
import com.eliezer.newbaseline.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, RoleMapper.class})
public interface UserMapper {
    @Mapping(source = "departmentId", target = "department")
    User toEntity(UserDTO dto);

    @Mapping(source = "department", target = "departmentId")
    UserDTO toDTO(User entity);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDTO(UserDTO dto, @MappingTarget User entity);

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
