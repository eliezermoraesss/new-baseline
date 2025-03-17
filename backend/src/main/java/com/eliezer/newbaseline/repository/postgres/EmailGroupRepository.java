package com.eliezer.newbaseline.repository.postgres;

import com.eliezer.newbaseline.model.EmailGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailGroupRepository extends JpaRepository<EmailGroup, Long> {
}
