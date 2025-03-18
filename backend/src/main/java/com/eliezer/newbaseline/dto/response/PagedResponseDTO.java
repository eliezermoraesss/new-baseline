package com.eliezer.newbaseline.dto.response;

import java.util.List;

public record PagedResponseDTO<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last,
        boolean first
) {
}
