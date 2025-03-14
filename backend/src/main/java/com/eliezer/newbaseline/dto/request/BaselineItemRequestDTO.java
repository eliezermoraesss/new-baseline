package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.enums.EntryTypes;
import com.eliezer.newbaseline.enums.PcpStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;

public record BaselineItemRequestDTO(
    Long id,
    @NotBlank Integer level,

    @Size(max = 13, message = "O campo deve ter no máximo 13 caracteres")
    @NotBlank
    String componentCode,

    @Size(max = 13, message = "O campo deve ter no máximo 13 caracteres")
    @NotBlank String parentCode,

    @Size(max = 160, message = "O campo deve ter no máximo 160 caracteres")
    @NotBlank
    String description,

    @Size(max = 2, message = "O campo deve ter no máximo 2 caracteres")
    @NotBlank String unit,
    @NotBlank @Positive BigDecimal quantity,
    @NotBlank @Positive BigDecimal totalQuantity,
    @NotBlank boolean hasDrawing,
    @NotBlank EntryTypes entryTypes,
    @NotBlank EntryRequestDTO entry,
    @NotBlank PcpStatus pcpStatus,
    @FutureOrPresent Instant createdAt
) {}
