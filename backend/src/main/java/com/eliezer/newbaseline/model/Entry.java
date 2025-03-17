package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.common.SoftDeleteEntity;
import com.eliezer.newbaseline.enums.EntryTypes;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_entry")
public class Entry extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productCode;

    @Column(nullable = false)
    private String productDescription;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Enumerated(value = EnumType.STRING)
    private EntryTypes entryTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baseline_id")
    private Baseline baseline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id", referencedColumnName = "id")
    private Notification notification;

    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private List<BaselineItem> baselineItems = new ArrayList<>();

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<Component> components;
}
