package com.cefet.prova_20223006782.controller;

import com.cefet.prova_20223006782.dto.CarroDTO;
import com.cefet.prova_20223006782.dto.MultaDTO;
import com.cefet.prova_20223006782.service.CarroService;
import com.cefet.prova_20223006782.service.MultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private MultaService multaService;
    
    @GetMapping
    public ResponseEntity<List<CarroDTO>> findAll() {
        List<CarroDTO> carroDTOs = carroService.findAll();
        return ResponseEntity.ok(carroDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        CarroDTO carroDTO = carroService.findById(id);
        return ResponseEntity.ok(carroDTO);
    }

    @GetMapping("/{id}/multas")
    public ResponseEntity<List<MultaDTO>> listarMultasPorCarro(@PathVariable Long id) {
        List<MultaDTO> carros = multaService.findByCarroId(id);
        return ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity<CarroDTO> create(@RequestBody CarroDTO carroDTO) {
        CarroDTO carroCriado = carroService.insert(carroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> update(@PathVariable Long id, @RequestBody CarroDTO carroDTO) {
        carroDTO.setPontuacao(null);
        
        CarroDTO carroAtualizado = carroService.update(id, carroDTO);
        return ResponseEntity.ok(carroAtualizado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build(); 
    }
}
