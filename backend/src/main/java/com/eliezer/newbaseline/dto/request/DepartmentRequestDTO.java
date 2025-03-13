package com.eliezer.newbaseline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentRequestDTO(
        Long id,
        @Size(max = 80, message = "A descrição do departamento deve ter no máximo 80 caracteres")
        @NotBlank
        String description
) {}
