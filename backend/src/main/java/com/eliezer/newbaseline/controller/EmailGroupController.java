package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.dto.request.EmailGroupRequestDTO;
import com.eliezer.newbaseline.dto.response.EmailGroupResponseDTO;
import com.eliezer.newbaseline.service.EmailGroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/email-groups")
public class EmailGroupController {
    private final EmailGroupService service;

    public EmailGroupController(EmailGroupService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmailGroupResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmailGroupResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmailGroupResponseDTO> insert(@RequestBody @Valid EmailGroupRequestDTO dto) {
        EmailGroupResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmailGroupResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EmailGroupRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
