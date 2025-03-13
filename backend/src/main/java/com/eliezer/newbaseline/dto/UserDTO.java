package com.eliezer.newbaseline.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserDTO(
    Long id,

    @Size(max = 150, message = "O nome completo deve ter no máximo 150 caracteres")
    @NotBlank(message = "Campo obrigatório")
    String fullName,

    @Size(max = 256, message = "O e-mail deve ter no máximo 256 caracteres")
    @Email
    @NotBlank
    String email,

    @Size(min = 6, max = 12, message = "A senha deve ter entre 6 e 12 caracteres")
    @NotBlank(message = "A senha é obrigatória")
    String password,

    @NotNull
    String profilePicture,

    Set<RoleDTO> roles,

    @NotBlank(message = "O campo departamento é obrigatório")
    Long departmentId
) {}
