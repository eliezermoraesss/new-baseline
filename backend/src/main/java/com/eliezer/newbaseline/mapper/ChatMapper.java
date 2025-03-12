package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.ChatDTO;
import com.eliezer.newbaseline.model.Chat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    Chat toEntity(ChatDTO dto);
    ChatDTO toDTO(Chat entity);
}
