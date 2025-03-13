package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EntryRequestDTO;
import com.eliezer.newbaseline.dto.response.EntryResponseDTO;
import com.eliezer.newbaseline.model.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BaselineMapper.class, UserMapper.class, EquipmentMapper.class})
public interface EntryMapper {
    @Mapping(source = "createdById", target = "createdBy")
    @Mapping(source = "baselineId", target = "baseline")
    @Mapping(source = "equipmentId", target = "equipment")
    Entry toEntity(EntryRequestDTO dto);

    @Mapping(source = "createdBy.fullName", target = "createdBy")
    @Mapping(source = "baseline.baselineCode", target = "baselineCode")
    @Mapping(source = "equipment.description", target = "equipmentDescription")
    @Mapping(source = "notification.id", target = "notificationId")
    EntryResponseDTO toDTO(Entry entity);

    default Entry toEntryFromId(Long entryId) {
        if (entryId == null) {
            return null;
        }
        Entry entry = new Entry();
        entry.setId(entryId);
        return entry;
    }
}
