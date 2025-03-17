package com.eliezer.newbaseline.repository.postgres;

import com.eliezer.newbaseline.model.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEventRepository extends JpaRepository<LogEvent, Long> {
}
