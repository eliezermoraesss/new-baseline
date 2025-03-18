package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.LogEventRequestDTO;
import com.eliezer.newbaseline.dto.response.LogEventResponseDTO;
import com.eliezer.newbaseline.mapper.LogEventMapper;
import com.eliezer.newbaseline.model.LogEvent;
import com.eliezer.newbaseline.repository.postgres.LogEventRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogEventService {
    public static final String MSG_NOT_FOUND = "LogEvent Id not found: ";
    private final LogEventRepository logEventRepository;
    private final LogEventMapper logEventMapper;

    public LogEventService(LogEventRepository logEventRepository, LogEventMapper logEventMapper) {
        this.logEventRepository = logEventRepository;
        this.logEventMapper = logEventMapper;
    }

    @Transactional(readOnly = true)
    public List<LogEventResponseDTO> findAll() {
        return logEventRepository.findAll().stream().map(logEventMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public LogEventResponseDTO findById(Long id) {
        return logEventMapper.toDTO(logEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public LogEventResponseDTO insert(LogEventRequestDTO logEventRequestDTO) {
        LogEvent logEvent = logEventMapper.toEntity(logEventRequestDTO);
        logEvent = logEventRepository.save(logEvent);
        return logEventMapper.toDTO(logEvent);
    }

    @Transactional
    public LogEventResponseDTO update(Long id, LogEventRequestDTO logEventRequestDTO) {
        LogEvent logEvent = logEventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        logEventMapper.updateLogEventFromDTO(logEventRequestDTO, logEvent);
        logEvent = logEventRepository.save(logEvent);
        return logEventMapper.toDTO(logEvent);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        try {
            logEventRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
}
