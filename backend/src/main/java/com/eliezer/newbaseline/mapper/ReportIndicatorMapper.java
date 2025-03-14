package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.ReportIndicatorRequestDTO;
import com.eliezer.newbaseline.dto.response.ReportIndicatorResponseDTO;
import com.eliezer.newbaseline.model.ReportIndicator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BaselineMapper.class})
public interface ReportIndicatorMapper {
    ReportIndicator toEntity(ReportIndicatorRequestDTO dto);
    ReportIndicatorResponseDTO toDTO(ReportIndicator entity);
}
