package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.MessageDTO;
import com.eliezer.newbaseline.model.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    Message toEntity(MessageDTO dto);
    MessageDTO toDTO(Message entity);
}
