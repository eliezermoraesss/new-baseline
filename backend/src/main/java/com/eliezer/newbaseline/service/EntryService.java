package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.EntryRequestDTO;
import com.eliezer.newbaseline.dto.response.EntryResponseDTO;
import com.eliezer.newbaseline.mapper.EntryMapper;
import com.eliezer.newbaseline.model.Entry;
import com.eliezer.newbaseline.repository.postgres.EntryRepository;
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
public class EntryService {
    public static final String MSG_NOT_FOUND = "Entry Id not found: ";
    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;

    public EntryService(EntryRepository entryRepository, EntryMapper entryMapper) {
        this.entryRepository = entryRepository;
        this.entryMapper = entryMapper;
    }

    @Transactional(readOnly = true)
    public Page<EntryResponseDTO> findAllPaged(Pageable pageable) {
        return entryRepository.findAll(pageable).map(entryMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public EntryResponseDTO findById(Long id) {
        return entryMapper.toDTO(entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public EntryResponseDTO insert(EntryRequestDTO entryRequestDTO) {
        Entry entry = entryMapper.toEntity(entryRequestDTO);
        entry = entryRepository.save(entry);
        return entryMapper.toDTO(entry);
    }

    @Transactional
    public EntryResponseDTO update(Long id, EntryRequestDTO entryRequestDTO) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        entryMapper.updateEntryFromDTO(entryRequestDTO, entry);
        entry = entryRepository.save(entry);
        return entryMapper.toDTO(entry);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        try {
            entryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
}
