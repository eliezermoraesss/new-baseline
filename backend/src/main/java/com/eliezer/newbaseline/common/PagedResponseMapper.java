package com.eliezer.newbaseline.common;

import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import org.springframework.data.domain.Page;

public class PagedResponseMapper {

    private PagedResponseMapper() {}

    public static <T> PagedResponseDTO<T> map(Page<T> page) {
        return new PagedResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
