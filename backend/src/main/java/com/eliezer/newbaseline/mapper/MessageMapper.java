package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.MessageRequestDTO;
import com.eliezer.newbaseline.model.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toEntity(MessageRequestDTO dto);
    MessageRequestDTO toDTO(Message entity);
}
