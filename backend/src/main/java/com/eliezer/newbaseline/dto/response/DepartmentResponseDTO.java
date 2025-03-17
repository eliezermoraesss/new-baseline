package com.eliezer.newbaseline.dto.response;

import java.time.Instant;

public record DepartmentResponseDTO(
        Long id,
        String description,
        Instant createdAt,
        Instant updatedAt
) {}
