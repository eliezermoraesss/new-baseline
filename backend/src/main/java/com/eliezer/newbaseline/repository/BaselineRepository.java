package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Baseline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaselineRepository extends JpaRepository<Baseline, Long> {
}
