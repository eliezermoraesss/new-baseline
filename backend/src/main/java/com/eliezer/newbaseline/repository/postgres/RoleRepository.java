package com.eliezer.newbaseline.repository.postgres;

import com.eliezer.newbaseline.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
