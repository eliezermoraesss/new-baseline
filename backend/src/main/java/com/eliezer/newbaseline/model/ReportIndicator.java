package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_report_indicator")
public class ReportIndicator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private BigDecimal advancedListQuantity;

    @Column(nullable = false)
    private BigDecimal baselineQuantity;

    @Column(nullable = false)
    private BigDecimal difference;

    @Column(nullable = false)
    private ReportStatus reportStatus;

    @OneToOne()
    private Baseline baselineCode;
}
