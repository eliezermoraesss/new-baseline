package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
