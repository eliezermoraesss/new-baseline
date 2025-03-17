package com.eliezer.newbaseline.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Component {
    private Integer level;
    private String componentCode;
    private String parentCode;
    private String description;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal totalQuantity;
    private boolean hasDrawing = false;
}
