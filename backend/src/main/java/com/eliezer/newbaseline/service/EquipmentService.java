package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.EquipmentRequestDTO;
import com.eliezer.newbaseline.dto.response.EquipmentResponseDTO;
import com.eliezer.newbaseline.mapper.EquipmentMapper;
import com.eliezer.newbaseline.model.Equipment;
import com.eliezer.newbaseline.repository.postgres.EquipmentRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EquipmentService {
    public static final String MSG_NOT_FOUND = "Equipment Id not found: ";
    private final EquipmentRepository equipmentRepository;
    private final EquipmentMapper equipmentMapper;

    public EquipmentService(EquipmentRepository equipmentRepository, EquipmentMapper equipmentMapper) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentMapper = equipmentMapper;
    }

    @Transactional(readOnly = true)
    public Page<EquipmentResponseDTO> findAllPaged(Pageable pageable) {
        return equipmentRepository.findAll(pageable).map(equipmentMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public EquipmentResponseDTO findById(Long id) {
        return equipmentMapper.toDTO(equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public EquipmentResponseDTO insert(EquipmentRequestDTO equipmentRequestDTO) {
        Equipment equipment = equipmentMapper.toEntity(equipmentRequestDTO);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDTO(equipment);
    }

    @Transactional
    public EquipmentResponseDTO update(Long id, EquipmentRequestDTO equipmentRequestDTO) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        equipmentMapper.updateEquipmentFromDTO(equipmentRequestDTO, equipment);
        equipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDTO(equipment);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isPresent()) {
            try {
                equipmentRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
