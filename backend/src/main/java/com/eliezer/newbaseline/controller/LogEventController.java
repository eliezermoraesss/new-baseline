package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.common.PagedResponseMapper;
import com.eliezer.newbaseline.dto.request.LogEventRequestDTO;
import com.eliezer.newbaseline.dto.response.LogEventResponseDTO;
import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import com.eliezer.newbaseline.service.LogEventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/log-events")
public class LogEventController {
    private final LogEventService service;

    public LogEventController(LogEventService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<LogEventResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(PagedResponseMapper.map(service.findAllPaged(pageable)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LogEventResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<LogEventResponseDTO> insert(@RequestBody @Valid LogEventRequestDTO dto) {
        LogEventResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LogEventResponseDTO> update(@PathVariable Long id, @RequestBody @Valid LogEventRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
