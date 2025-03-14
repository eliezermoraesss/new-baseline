package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
}
