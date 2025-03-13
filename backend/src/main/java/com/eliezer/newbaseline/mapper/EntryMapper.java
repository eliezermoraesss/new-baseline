package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.EntryRequestDTO;
import com.eliezer.newbaseline.dto.response.EntryResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import com.eliezer.newbaseline.model.Entry;
import com.eliezer.newbaseline.model.Equipment;
import com.eliezer.newbaseline.model.User;
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
    EntryResponseDTO toDTO(Entry entity);

    default Baseline mapBaseline(Long baselineId) {
        if (baselineId == null) {
            return null;
        }
        Baseline baseline = new Baseline();
        baseline.setId(baselineId);
        return baseline;
    }

    default User mapUser(Long createdById) {
        if (createdById == null) {
            return null;
        }
        User user = new User();
        user.setId(createdById);
        return user;
    }

    default Equipment mapEquipment(Long equipmentId) {
        if (equipmentId == null) {
            return null;
        }
        Equipment equipment = new Equipment();
        equipment.setId(equipmentId);
        return equipment;
    }
}
