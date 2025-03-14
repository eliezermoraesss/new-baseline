package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EmailMessageRequestDTO;
import com.eliezer.newbaseline.messaging.message.EmailMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMessageMapper {
    EmailMessage toEntity(EmailMessageRequestDTO dto);
    EmailMessageRequestDTO toDTO(EmailMessage entity);
}
