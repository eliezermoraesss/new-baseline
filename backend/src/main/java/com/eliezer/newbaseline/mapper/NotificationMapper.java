package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.NotificationRequestDTO;
import com.eliezer.newbaseline.dto.response.NotificationResponseDTO;
import com.eliezer.newbaseline.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toEntity(NotificationRequestDTO dto);
    NotificationResponseDTO toDTO(Notification entity);
}
