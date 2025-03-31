package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.common.PagedResponseMapper;
import com.eliezer.newbaseline.dto.request.ReportIndicatorRequestDTO;
import com.eliezer.newbaseline.dto.response.ReportIndicatorResponseDTO;
import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import com.eliezer.newbaseline.service.ReportIndicatorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/report-indicators")
public class ReportIndicatorController {
    private final ReportIndicatorService service;

    public ReportIndicatorController(ReportIndicatorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<ReportIndicatorResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(PagedResponseMapper.map(service.findAllPaged(pageable)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportIndicatorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ReportIndicatorResponseDTO> insert(@RequestBody @Valid ReportIndicatorRequestDTO dto) {
        ReportIndicatorResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReportIndicatorResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ReportIndicatorRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
