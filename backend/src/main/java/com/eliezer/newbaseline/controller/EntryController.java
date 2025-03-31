package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.common.PagedResponseMapper;
import com.eliezer.newbaseline.dto.request.EntryRequestDTO;
import com.eliezer.newbaseline.dto.response.EntryResponseDTO;
import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import com.eliezer.newbaseline.service.EntryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/entries")
public class EntryController {
    private final EntryService service;

    public EntryController(EntryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<EntryResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(PagedResponseMapper.map(service.findAllPaged(pageable)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EntryResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EntryResponseDTO> insert(@RequestBody @Valid EntryRequestDTO dto) {
        EntryResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EntryResponseDTO> update(@PathVariable Long id, @RequestBody @Valid EntryRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
