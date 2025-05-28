package com.cefet.prova_20223006782.controller;

import com.cefet.prova_20223006782.dto.MultaDTO;
import com.cefet.prova_20223006782.service.MultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multas")
public class MultaController {
    @Autowired
    private MultaService multaService;

    @GetMapping
    public ResponseEntity<List<MultaDTO>> findAll() {
        return ResponseEntity.ok(multaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(multaService.findById(id));
    }

    @GetMapping("/carro/{carroId}")
    public ResponseEntity<List<MultaDTO>> findByCarroId(@PathVariable Long carroId) {
        return ResponseEntity.ok(multaService.findByCarroId(carroId));
    }

    @PostMapping
    public ResponseEntity<MultaDTO> create(@RequestBody MultaDTO multaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.insert(multaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        multaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
