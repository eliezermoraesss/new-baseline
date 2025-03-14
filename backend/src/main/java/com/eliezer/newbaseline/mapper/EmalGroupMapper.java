package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EmailGroupRequestDTO;
import com.eliezer.newbaseline.dto.response.EmailGroupResponseDTO;
import com.eliezer.newbaseline.model.EmailGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EmalGroupMapper {
    EmailGroup toEntity(EmailGroupRequestDTO dto);
    EmailGroupResponseDTO toDTO(EmailGroup entity);
}
