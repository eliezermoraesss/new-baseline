package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.enums.ReportStatus;
import com.eliezer.newbaseline.model.Baseline;

import java.math.BigDecimal;

public record ReportIndicatorRequestDTO(
    Long id,
    Baseline baseline,
    String code,
    String description,
    String unit,
    BigDecimal advancedListQuantity,
    BigDecimal baselineQuantity,
    BigDecimal difference,
    ReportStatus reportStatus,
    String scNumber,
    BigDecimal scQuantity,
    String opNumber,
    BigDecimal opQuantity
) {
}
