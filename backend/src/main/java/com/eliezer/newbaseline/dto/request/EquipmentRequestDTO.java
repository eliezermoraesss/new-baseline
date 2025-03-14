package com.eliezer.newbaseline.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipmentRequestDTO(
    Long id,

    @Size(max = 50, message = "O campo família deve ter no máximo 50 caracteres")
    @NotBlank(message = "O campo família é obrigatório")
    String family,

    @Size(max = 50, message = "O campo modelo deve ter no máximo 50 caracteres")
    @NotBlank(message = "O campo modelo é obrigatório")
    String model,

    @Size(max = 150, message = "O campo modelo deve ter no máximo 150 caracteres")
    @NotBlank(message = "O campo descrição é obrigatório")
    String description,

    @NotBlank(message = "O ID do usuário é obrigatório")
    Long createdById

) {}
