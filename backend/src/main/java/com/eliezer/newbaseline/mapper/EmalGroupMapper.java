package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EmailGroupRequestDTO;
import com.eliezer.newbaseline.model.EmailGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmalGroupMapper {
    EmailGroup toEntity(EmailGroupRequestDTO dto);
    EmailGroupRequestDTO toDTO(EmailGroup entity);
}
