package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.enums.EntryTypes;
import com.eliezer.newbaseline.enums.PcpStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_baseline_item")
public class BaselineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;
    private String componentCode;
    private String parentCode;
    private String description;
    private String unity;
    private BigDecimal quantity;
    private BigDecimal totalQuantity;
    private boolean hasDrawing = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baseline_code", referencedColumnName = "baseline_code")
    private Baseline baselineCode;

    @Enumerated(EnumType.STRING)
    private EntryTypes entryTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entry_id")
    private Entry entry;

    @Enumerated(EnumType.STRING)
    private PcpStatus pcpStatus;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
}
