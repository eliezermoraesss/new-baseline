package com.eliezer.newbaseline.dto.response;

import com.eliezer.newbaseline.dto.UserDTO;

import java.time.Instant;
import java.util.Set;

public record EmailGroupResponseDTO(
        Long id,
        String groupName,
        Set<UserDTO> emails,
        Instant createdAt,
        Instant updatedAt
){}
