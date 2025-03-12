package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.EmailGroupDTO;
import com.eliezer.newbaseline.model.EmailGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmalGroupMapper {
    EmailGroup toEntity(EmailGroupDTO dto);
    EmailGroupDTO toDTO(EmailGroup entity);
}
