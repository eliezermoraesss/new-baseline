package com.eliezer.newbaseline.repository;

import com.eliezer.newbaseline.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
