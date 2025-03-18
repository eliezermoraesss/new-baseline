package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.ReportIndicatorRequestDTO;
import com.eliezer.newbaseline.dto.response.ReportIndicatorResponseDTO;
import com.eliezer.newbaseline.mapper.ReportIndicatorMapper;
import com.eliezer.newbaseline.model.ReportIndicator;
import com.eliezer.newbaseline.repository.postgres.ReportIndicatorRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportIndicatorService {
    public static final String MSG_NOT_FOUND = "ReportIndicator Id not found: ";
    private final ReportIndicatorRepository reportIndicatorRepository;
    private final ReportIndicatorMapper reportIndicatorMapper;

    public ReportIndicatorService(ReportIndicatorRepository reportIndicatorRepository, ReportIndicatorMapper reportIndicatorMapper) {
        this.reportIndicatorRepository = reportIndicatorRepository;
        this.reportIndicatorMapper = reportIndicatorMapper;
    }

    @Transactional(readOnly = true)
    public Page<ReportIndicatorResponseDTO> findAllPaged(Pageable pageable) {
        return reportIndicatorRepository.findAll(pageable).map(reportIndicatorMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public ReportIndicatorResponseDTO findById(Long id) {
        return reportIndicatorMapper.toDTO(reportIndicatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public ReportIndicatorResponseDTO insert(ReportIndicatorRequestDTO reportIndicatorRequestDTO) {
        ReportIndicator reportIndicator = reportIndicatorMapper.toEntity(reportIndicatorRequestDTO);
        reportIndicator = reportIndicatorRepository.save(reportIndicator);
        return reportIndicatorMapper.toDTO(reportIndicator);
    }

    @Transactional
    public ReportIndicatorResponseDTO update(Long id, ReportIndicatorRequestDTO reportIndicatorRequestDTO) {
        ReportIndicator reportIndicator = reportIndicatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        reportIndicatorMapper.updateReportIndicatorFromDTO(reportIndicatorRequestDTO, reportIndicator);
        reportIndicator = reportIndicatorRepository.save(reportIndicator);
        return reportIndicatorMapper.toDTO(reportIndicator);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        try {
            reportIndicatorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
}
