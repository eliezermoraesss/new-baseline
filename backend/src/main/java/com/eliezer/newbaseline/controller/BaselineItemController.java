package com.eliezer.newbaseline.controller;

import com.eliezer.newbaseline.common.PagedResponseMapper;
import com.eliezer.newbaseline.dto.request.BaselineItemRequestDTO;
import com.eliezer.newbaseline.dto.response.BaselineItemResponseDTO;
import com.eliezer.newbaseline.dto.response.PagedResponseDTO;
import com.eliezer.newbaseline.service.BaselineItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/baselines-items")
public class BaselineItemController {

    private final BaselineItemService service;

    public BaselineItemController(BaselineItemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<BaselineItemResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(PagedResponseMapper.map(service.findAllPaged(pageable)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BaselineItemResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BaselineItemResponseDTO> insert(@RequestBody @Valid BaselineItemRequestDTO dto) {
        BaselineItemResponseDTO responseDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BaselineItemResponseDTO> update(@PathVariable Long id, @RequestBody @Valid BaselineItemRequestDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
