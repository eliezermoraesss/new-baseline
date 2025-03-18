package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.RoleDTO;
import com.eliezer.newbaseline.mapper.RoleMapper;
import com.eliezer.newbaseline.model.Role;
import com.eliezer.newbaseline.repository.postgres.RoleRepository;
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
public class RoleService {

    public static final String MSG_NOT_FOUND = "Role Id not found: ";

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return roleMapper.toDTO(role);
    }

    @Transactional
    public RoleDTO insert(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDTO(role);
    }

    @Transactional
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        roleMapper.updateRoleFromDTO(roleDTO, role);
        role = roleRepository.save(role);
        return roleMapper.toDTO(role);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            try {
                roleRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
