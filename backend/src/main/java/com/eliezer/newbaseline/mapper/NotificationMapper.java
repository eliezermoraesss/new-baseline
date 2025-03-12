package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.NotificationDTO;
import com.eliezer.newbaseline.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification toEntity(NotificationDTO dto);
    NotificationDTO toDTO(Notification entity);
}
