package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.EmailMessageDTO;
import com.eliezer.newbaseline.messaging.message.EmailMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMessageMapper {
    EmailMessage toEntity(EmailMessageDTO dto);
    EmailMessageDTO toDTO(EmailMessage entity);
}
