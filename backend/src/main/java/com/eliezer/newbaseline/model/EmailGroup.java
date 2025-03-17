package com.eliezer.newbaseline.model;

import com.eliezer.newbaseline.common.SoftDeleteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_email_group")
public class EmailGroup extends SoftDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_member",
            joinColumns = @JoinColumn(name = "email_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> emails = new HashSet<>();

    public void addUser(User user) {
        emails.add(user);
    }
}
