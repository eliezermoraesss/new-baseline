package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.ReportIndicatorRequestDTO;
import com.eliezer.newbaseline.dto.response.ReportIndicatorResponseDTO;
import com.eliezer.newbaseline.model.ReportIndicator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {BaselineMapper.class})
public interface ReportIndicatorMapper {
    ReportIndicator toEntity(ReportIndicatorRequestDTO dto);

    @Mapping(target = "baselineCode", source = "baseline.baselineCode")
    ReportIndicatorResponseDTO toDTO(ReportIndicator entity);

    @Mapping(target = "id", ignore = true)
    void updateReportIndicatorFromDTO(ReportIndicatorRequestDTO dto, @MappingTarget ReportIndicator entity);
}
