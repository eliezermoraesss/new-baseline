package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import com.eliezer.newbaseline.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EntryMapper.class})
public interface BaselineMapper {
    @Mapping(target = "createdBy", source = "createdById")
    Baseline toEntity(BaselineRequestDTO dto);

    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "entries", target = "entries")
    BaselineResponseDTO toResponseDTO(Baseline entity);

    default User map(Long createdById) {
        if (createdById == null) {
            return null;
        }
        User user = new User();
        user.setId(createdById);
        return user;
    }
}
