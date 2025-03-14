package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.enums.ProjectStatus;

import java.time.Instant;
import java.time.LocalDate;

public record BaselineResponseDTO(
    Long id,
    String baselineCode,
    String projectName,
    LocalDate openDate,
    LocalDate deliveryDate,
    LocalDate startProjectDate,
    LocalDate endProjectDate,
    Integer projectDuration,
    ProjectStatus projectStatus,
    String proposalNumber,
    String createdBy,
    Instant createdAt
) {}
