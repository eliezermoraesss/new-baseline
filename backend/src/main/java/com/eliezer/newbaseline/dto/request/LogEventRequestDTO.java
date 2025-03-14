package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LogEventRequestDTO(
    Long id,
    @Size(max = 255, message = "O campo mensagem deve ter no máximo 255 caracteres")
    @NotBlank(message = "O campo mensagem é obrigatório")
    String message,

    @NotBlank
    UserDTO createdBy,

    BaselineRequestDTO baseline,
    EntryRequestDTO entry
) {}
