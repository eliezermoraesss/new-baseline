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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    public static final String MSG_NOT_FOUND = "Role Id not found: ";

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Transactional
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toDTO).toList();
    }

    @Transactional
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

    public RoleDTO update(Long id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        roleMapper.updateRoleFromDTO(roleDTO, role);
        role = roleRepository.save(role);
        return roleMapper.toDTO(role);
    }
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id + " - " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation - " + e.getMessage());
        }
    }
}
