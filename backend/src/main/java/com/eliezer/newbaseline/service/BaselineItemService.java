package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.BaselineItemRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineItemResponseDTO;
import com.eliezer.newbaseline.mapper.BaselineItemMapper;
import com.eliezer.newbaseline.model.BaselineItem;
import com.eliezer.newbaseline.repository.postgres.BaselineItemRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BaselineItemService {
    public static final String MSG_NOT_FOUND = "BaselineItem Id not found: ";
    private final BaselineItemRepository baselineItemRepository;
    private final BaselineItemMapper baselineItemMapper;

    public BaselineItemService(BaselineItemRepository baselineItemRepository, BaselineItemMapper baselineItemMapper) {
        this.baselineItemRepository = baselineItemRepository;
        this.baselineItemMapper = baselineItemMapper;
    }

    @Transactional(readOnly = true)
    public List<BaselineItemResponseDTO> findAll() {
        return baselineItemRepository.findAll().stream().map(baselineItemMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public BaselineItemResponseDTO findById(Long id) {
        return baselineItemMapper.toDTO(baselineItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public BaselineItemResponseDTO insert(BaselineItemRequestDTO baselineItemRequestDTO) {
        BaselineItem baselineItem = baselineItemMapper.toEntity(baselineItemRequestDTO);
        baselineItem = baselineItemRepository.save(baselineItem);
        return baselineItemMapper.toDTO(baselineItem);
    }

    @Transactional
    public BaselineItemResponseDTO update(Long id, BaselineItemRequestDTO baselineItemRequestDTO) {
        BaselineItem baselineItem = baselineItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        baselineItemMapper.updateBaselineItemFromDTO(baselineItemRequestDTO, baselineItem);
        baselineItem = baselineItemRepository.save(baselineItem);
        return baselineItemMapper.toDTO(baselineItem);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        try {
            baselineItemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
}
