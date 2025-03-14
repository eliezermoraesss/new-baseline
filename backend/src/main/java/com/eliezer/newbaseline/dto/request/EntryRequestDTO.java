package com.eliezer.newbaseline.dto.request;

import com.eliezer.newbaseline.enums.EntryTypes;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public record EntryRequestDTO(
     Long id,

     @Size(max = 13, message = "O código do produto deve ter no máximo 13 caracteres")
     @NotBlank(message = "O código do produto é obrigatório")
     String productCode,

     @Size(max = 2, message = "A unidade deve ter no máximo 2 caracteres")
     @NotBlank(message = "A unidade é obrigatória")
     String unit,

     @Positive(message = "A quantidade deve ser positiva")
     @NotNull
     BigDecimal quantity,

     @NotBlank(message = "O tipo de entrada é obrigatório")
     EntryTypes entryTypes,

     @FutureOrPresent
     Instant createdAt,

     @NotBlank(message = "ID do usuário é obrigatório")
     Long createdById,

     @NotBlank(message = "ID da baseline é obrigatório")
     Long baselineId,

     @NotBlank(message = "ID do equipamento é obrigatório")
     Long equipmentId
) {}
