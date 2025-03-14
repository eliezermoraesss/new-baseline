package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.enums.EntryTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record EntryResponseDTO(
     Long id,
     String productCode,
     String productDescription,
     String unit,
     BigDecimal quantity,
     EntryTypes entryTypes,
     String createdBy,
     String baselineCode,
     String equipmentDescription,
     Long notificationId,
     List<BaselineItemResponseDTO> baselineItems,
     Instant createdAt
) {}
