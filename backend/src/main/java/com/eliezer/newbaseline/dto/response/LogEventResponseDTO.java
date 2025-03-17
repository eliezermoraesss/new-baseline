package com.eliezer.newbaseline.dto.response;

import java.time.Instant;

public record LogEventResponseDTO(
    Long id,
    String message,
    String createdBy,
    String baselineCode,
    Long entryId,
    Instant createdAt,
    Instant updatedAt
) {}
