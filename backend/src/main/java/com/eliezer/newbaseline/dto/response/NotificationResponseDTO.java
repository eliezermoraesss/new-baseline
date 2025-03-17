package com.eliezer.newbaseline.dto.response;

import java.time.Instant;
import java.util.Set;

public record NotificationResponseDTO(
    Long id,
    String title,
    Set<EmailGroupResponseDTO> emailGroups,
    String message,
    Instant createdAt,
    String createdBy,
    String baselineCode,
    Long entryId,
    Instant createAt,
    Instant updatedAt
) {
}
