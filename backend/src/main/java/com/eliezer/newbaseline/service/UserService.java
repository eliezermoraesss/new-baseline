package com.eliezer.newbaseline.service;

import com.eliezer.newbaseline.dto.UserDTO;
import com.eliezer.newbaseline.mapper.UserMapper;
import com.eliezer.newbaseline.model.User;
import com.eliezer.newbaseline.repository.postgres.UserRepository;
import com.eliezer.newbaseline.service.exception.DataBaseException;
import com.eliezer.newbaseline.service.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public static final String MSG_NOT_FOUND = "User Id not found: ";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MSG_NOT_FOUND + id));
        userMapper.updateUserFromDTO(userDTO, user);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            try {
                userRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataBaseException("Integrity violation - " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException(MSG_NOT_FOUND + id);
        }
    }
}
