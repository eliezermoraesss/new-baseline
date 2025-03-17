package com.eliezer.newbaseline.repository.postgres;

import com.eliezer.newbaseline.model.ReportIndicator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportIndicatorRepository extends JpaRepository<ReportIndicator, Long> {
}
