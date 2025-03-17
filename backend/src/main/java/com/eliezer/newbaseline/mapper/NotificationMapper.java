package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.NotificationRequestDTO;
import com.eliezer.newbaseline.dto.response.NotificationResponseDTO;
import com.eliezer.newbaseline.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, BaselineMapper.class, EntryMapper.class})
public interface NotificationMapper {
    @Mapping(target = "createdBy", source = "createdById")
    @Mapping(target = "baseline", source = "baselineId")
    @Mapping(target = "entry", source = "entryId")
    Notification toEntity(NotificationRequestDTO dto);

    @Mapping(source = "createdBy.fullName", target = "createdBy")
    @Mapping(source = "baseline.baselineCode", target = "baselineCode")
    @Mapping(source = "entry.id", target = "entryId")
    NotificationResponseDTO toDTO(Notification entity);

    @Mapping(target = "id", ignore = true)
    void updateNotificationFromDTO(NotificationRequestDTO dto, @MappingTarget Notification entity);
}
