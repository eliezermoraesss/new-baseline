package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.enums.EntryTypes;
import com.eliezer.newbaseline.enums.PcpStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record BaselineItemResponseDTO(
    Long id,
    Integer level,
    String componentCode,
    String parentCode,
    String description,
    String unity,
    BigDecimal quantity,
    BigDecimal totalQuantity,
    boolean hasDrawing,
    EntryTypes entryTypes,
    Long entryId,
    PcpStatus pcpStatus,
    Instant createdAt
) {
}
