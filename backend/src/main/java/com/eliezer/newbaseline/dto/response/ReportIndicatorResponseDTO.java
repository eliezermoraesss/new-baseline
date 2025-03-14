package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.enums.ReportStatus;

import java.math.BigDecimal;

public record ReportIndicatorResponseDTO(
    Long id,
    String baselineCode,
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
