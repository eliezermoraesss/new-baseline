package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_baseline")
public class Baseline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String baselineCode;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String projectName;

    @Column(nullable = false)
    private LocalDate openDate;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    @Column(nullable = false)
    private LocalDate startProjectDate;

    @Column(nullable = false)
    private LocalDate endProjectDate;

    @Column(nullable = false)
    private Integer projectDuration;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

    private String mediaUrl;

    @Column(nullable = false, unique = true)
    private String proposalNumber;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
}
