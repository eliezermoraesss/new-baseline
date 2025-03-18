package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.DepartmentRequestDTO;
import com.eliezer.newbaseline.dto.response.DepartmentResponseDTO;
import com.eliezer.newbaseline.mapper.DepartmentMapper;
import com.eliezer.newbaseline.model.Department;
import com.eliezer.newbaseline.repository.postgres.DepartmentRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    public static final String MSG_NOT_FOUND = "Department Id not found: ";
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> findAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDTO findById(Long id) {
        return departmentMapper.toDTO(departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }
    
    @Transactional
    public DepartmentResponseDTO insert(DepartmentRequestDTO departmentRequestDTO) {
    	Department department = departmentMapper.toEntity(departmentRequestDTO);
    	department = departmentRepository.save(department);
    	return departmentMapper.toDTO(department);
    }
    
    @Transactional
    public DepartmentResponseDTO update(Long id, DepartmentRequestDTO departmentRequestDTO) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
		departmentMapper.updateDepartmentFromDTO(departmentRequestDTO, department);
		department = departmentRepository.save(department);
		return departmentMapper.toDTO(department);
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
		try {
			departmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation - " + e.getMessage());
		}
	}
}
