package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.request.NotificationRequestDTO;
import com.eliezer.newbaseline.dto.response.NotificationResponseDTO;
import com.eliezer.newbaseline.mapper.NotificationMapper;
import com.eliezer.newbaseline.model.Notification;
import com.eliezer.newbaseline.repository.postgres.NotificationRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NotificationService {
    public static final String MSG_NOT_FOUND = "Notification Id not found: ";
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Transactional(readOnly = true)
    public Page<NotificationResponseDTO> findAllPaged(Pageable pageable) {
        return notificationRepository.findAll(pageable).map(notificationMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public NotificationResponseDTO findById(Long id) {
        return notificationMapper.toDTO(notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id)));
    }

    @Transactional
    public NotificationResponseDTO insert(NotificationRequestDTO notificationRequestDTO) {
        Notification notification = notificationMapper.toEntity(notificationRequestDTO);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDTO(notification);
    }

    @Transactional
    public NotificationResponseDTO update(Long id, NotificationRequestDTO notificationRequestDTO) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        notificationMapper.updateNotificationFromDTO(notificationRequestDTO, notification);
        notification = notificationRepository.save(notification);
        return notificationMapper.toDTO(notification);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent()) {
            try {
                notificationRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
