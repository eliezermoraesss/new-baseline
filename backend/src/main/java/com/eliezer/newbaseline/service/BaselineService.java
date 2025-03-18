package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.BaselineRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineResponseDTO;
import com.eliezer.newbaseline.mapper.BaselineMapper;
import com.eliezer.newbaseline.model.Baseline;
import com.eliezer.newbaseline.repository.postgres.BaselineRepository;
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
public class BaselineService {
    public static final String MSG_NOT_FOUND = "Baseline Id not found: ";
    private final BaselineRepository baselineRepository;
    private final BaselineMapper baselineMapper;

    public BaselineService(BaselineRepository baselineRepository, BaselineMapper baselineMapper) {
        this.baselineRepository = baselineRepository;
        this.baselineMapper = baselineMapper;
    }

    @Transactional(readOnly = true)
    public Page<BaselineResponseDTO> findAllPaged(Pageable pageable) {
        return baselineRepository.findAll(pageable).map(baselineMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public BaselineResponseDTO findById(Long id) {
        return baselineMapper.toDTO(baselineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public BaselineResponseDTO insert(BaselineRequestDTO baselineRequestDTO) {
        Baseline baseline = baselineMapper.toEntity(baselineRequestDTO);
        baseline = baselineRepository.save(baseline);
        return baselineMapper.toDTO(baseline);
    }

    @Transactional
    public BaselineResponseDTO update(Long id, BaselineRequestDTO baselineRequestDTO) {
        Baseline baseline = baselineRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        baselineMapper.updateBaselineFromDTO(baselineRequestDTO, baseline);
        baseline = baselineRepository.save(baseline);
        return baselineMapper.toDTO(baseline);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<Baseline> baseline = baselineRepository.findById(id);
        if (baseline.isPresent()) {
            try {
                baselineRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
