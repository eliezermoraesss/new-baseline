package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.common.SoftDeleteEntity;
import com.eliezer.newbaseline.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_baseline")
public class Baseline extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "baseline_code", nullable = false, unique = true)
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

    private Integer projectDuration;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatus projectStatus;

    @Column(columnDefinition = "TEXT")
    private String mediaUrl;

    @Column(unique = true)
    private String proposalNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "baseline")
    private Set<Entry> entries = new HashSet<>();
}
