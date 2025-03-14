package com.eliezer.newbaseline.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_email_group")
public class EmailGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_member",
            joinColumns = @JoinColumn(name = "email_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> emails = new HashSet<>();
}
