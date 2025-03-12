package com.eliezer.newbaseline.mapper;

import com.eliezer.newbaseline.dto.ReportIndicatorDTO;
import com.eliezer.newbaseline.model.ReportIndicator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportIndicatorMapper {
    ReportIndicator toEntity(ReportIndicatorDTO dto);
    ReportIndicatorDTO toDTO(ReportIndicator entity);
}
