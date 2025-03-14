package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.request.ReportIndicatorRequestDTO;
import com.eliezer.newbaseline.model.ReportIndicator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportIndicatorMapper {
    ReportIndicator toEntity(ReportIndicatorRequestDTO dto);
    ReportIndicatorRequestDTO toDTO(ReportIndicator entity);
}
