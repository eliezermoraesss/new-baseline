package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.BaselineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaselineItemRepository extends JpaRepository<BaselineItem, Long> {
}
