package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.common.SoftDeleteEntity;
import com.eliezer.newbaseline.enums.EntryTypes;
import com.eliezer.newbaseline.enums.PcpStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_baseline_item")
public class BaselineItem extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private String componentCode;

    @Column(nullable = false)
    private String parentCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(nullable = false)
    private BigDecimal totalQuantity;

    @Column(nullable = false)
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
