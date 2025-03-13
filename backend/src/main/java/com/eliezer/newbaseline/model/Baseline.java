package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT")
    private String mediaUrl;

    @Column(unique = true)
    private String proposalNumber;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;

    @OneToMany(mappedBy = "baseline")
    private Set<Entry> entries = new HashSet<>();

    @OneToMany(mappedBy = "baseline")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "baseline")
    private Set<LogEvent> logEvents = new HashSet<>();
}
