package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.model.Baseline;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserMapper.class, EntryMapper.class})
public interface BaselineMapper {
    @Mapping(target = "createdBy", source = "createdById")
    Baseline toEntity(BaselineRequestDTO dto);

    @Mapping(source = "createdBy.fullName", target = "createdBy")
    BaselineResponseDTO toDTO(Baseline entity);

    @Mapping(target = "id", ignore = true)
    void updateBaselineFromDTO(BaselineRequestDTO dto, @MappingTarget Baseline entity);

    default Baseline toBaselineFromId(Long baselineId) {
        if (baselineId == null) {
            return null;
        }
        Baseline baseline = new Baseline();
        baseline.setId(baselineId);
        return baseline;
    }
}
