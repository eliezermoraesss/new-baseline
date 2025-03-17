package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.enums.ReportStatus;

import java.math.BigDecimal;
import java.time.Instant;

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
    BigDecimal opQuantity,
    Instant createdAt,
    Instant updatedAt
) {
}
