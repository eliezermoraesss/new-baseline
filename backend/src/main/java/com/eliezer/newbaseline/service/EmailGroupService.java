package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.EmailGroupRequestDTO;
import com.eliezer.newbaseline.dto.response.EmailGroupResponseDTO;
import com.eliezer.newbaseline.mapper.EmailGroupMapper;
import com.eliezer.newbaseline.model.EmailGroup;
import com.eliezer.newbaseline.repository.postgres.EmailGroupRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmailGroupService {
    public static final String MSG_NOT_FOUND = "EmailGroup Id not found: ";
    private final EmailGroupRepository emailGroupRepository;
    private final EmailGroupMapper emailGroupMapper;

    public EmailGroupService(EmailGroupRepository emailGroupRepository, EmailGroupMapper emailGroupMapper) {
        this.emailGroupRepository = emailGroupRepository;
        this.emailGroupMapper = emailGroupMapper;
    }

    @Transactional(readOnly = true)
    public List<EmailGroupResponseDTO> findAll() {
        return emailGroupRepository.findAll().stream().map(emailGroupMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public EmailGroupResponseDTO findById(Long id) {
        return emailGroupMapper.toDTO(emailGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public EmailGroupResponseDTO insert(EmailGroupRequestDTO emailGroupRequestDTO) {
        EmailGroup emailGroup = emailGroupMapper.toEntity(emailGroupRequestDTO);
        emailGroup = emailGroupRepository.save(emailGroup);
        return emailGroupMapper.toDTO(emailGroup);
    }

    @Transactional
    public EmailGroupResponseDTO update(Long id, EmailGroupRequestDTO emailGroupRequestDTO) {
        EmailGroup emailGroup = emailGroupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        emailGroupMapper.updateEmailGroupFromDTO(emailGroupRequestDTO, emailGroup);
        emailGroup = emailGroupRepository.save(emailGroup);
        return emailGroupMapper.toDTO(emailGroup);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<EmailGroup> emailGroup = emailGroupRepository.findById(id);
        if (emailGroup.isPresent()) {
            try {
                emailGroupRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
