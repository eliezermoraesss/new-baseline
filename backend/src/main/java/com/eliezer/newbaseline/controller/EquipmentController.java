package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.common.PagedResponseMapper;
import com.eliezer.newbaseline.dto.request.EquipmentRequestDTO;
import com.eliezer.newbaseline.dto.response.EquipmentResponseDTO;
import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import com.eliezer.newbaseline.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/equipments")
public class EquipmentController {
    private final EquipmentService service;

    public EquipmentController(EquipmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<EquipmentResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(PagedResponseMapper.map(service.findAllPaged(pageable)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipmentResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EquipmentResponseDTO> insert(@RequestBody @Valid EquipmentRequestDTO dto) {
        EquipmentResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EquipmentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EquipmentRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
