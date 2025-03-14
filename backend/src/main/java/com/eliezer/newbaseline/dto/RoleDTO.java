package com.eliezer.newbaseline.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleDTO(
    @NotBlank Long id,
    @NotBlank String authority
) {}
