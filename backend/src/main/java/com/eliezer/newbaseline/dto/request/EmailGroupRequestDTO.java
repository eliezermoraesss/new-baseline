package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.dto.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record EmailGroupRequestDTO(
        Long id,

        @Size(max = 50, message = "O campo nome do grupo deve ter no máximo 50 caracteres")
        @NotBlank(message = "O campo nome do grupo é obrigatório")
        String groupName,

        Set<UserDTO> emails
) {
}
