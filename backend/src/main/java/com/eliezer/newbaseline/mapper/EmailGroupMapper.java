package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EmailGroupRequestDTO;
import com.eliezer.newbaseline.dto.response.EmailGroupResponseDTO;
import com.eliezer.newbaseline.model.EmailGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EmailGroupMapper {
    EmailGroup toEntity(EmailGroupRequestDTO dto);
    EmailGroupResponseDTO toDTO(EmailGroup entity);

    @Mapping(target = "id", ignore = true)
    void updateEmailGroupFromDTO(EmailGroupRequestDTO dto, @MappingTarget EmailGroup entity);
}
