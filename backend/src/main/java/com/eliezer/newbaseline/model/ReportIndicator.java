package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.common.SoftDeleteEntity;
import com.eliezer.newbaseline.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_report_indicator")
public class ReportIndicator extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baseline_id")
    private Baseline baseline;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private BigDecimal advancedListQuantity;

    @Column(nullable = false)
    private BigDecimal baselineQuantity;

    @Column(nullable = false)
    private BigDecimal difference;

    private String scNumber;
    private BigDecimal scQuantity;
    private String opNumber;
    private BigDecimal opQuantity;

    @Column(nullable = false)
    private ReportStatus reportStatus;
}
