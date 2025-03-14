package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.ChatRequestDTO;
import com.eliezer.newbaseline.model.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    Chat toEntity(ChatRequestDTO dto);
    ChatRequestDTO toDTO(Chat entity);
}
