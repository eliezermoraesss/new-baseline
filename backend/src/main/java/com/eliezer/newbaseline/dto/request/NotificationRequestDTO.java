package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.model.EmailGroup;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record NotificationRequestDTO(
    Long id,

    @NotBlank(message = "O campo título é obrigatório")
    String title,

    @NotBlank
    Set<EmailGroup> emailGroups,

    @NotBlank(message = "O campo mensagem é obrigatório")
    String message,

    @NotBlank(message = "O ID do usuário é obrigatório")
    Long createdById,

    @NotBlank(message = "O ID da baseline é obrigatório")
    Long baselineId,

    @NotBlank(message = "O ID do lançamento é obrigatório")
    Long entryId
) {}
