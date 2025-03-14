package com.eliezer.newbaseline.dto.response;

import java.time.Instant;

public record EquipmentResponseDTO(
        Long id,
        String family,
        String model,
        String description,
        String createdBy,
        Instant createdAt
) {}
