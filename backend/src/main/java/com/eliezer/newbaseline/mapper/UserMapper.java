package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.UserDTO;
import com.eliezer.newbaseline.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDTO(User entity);
}
