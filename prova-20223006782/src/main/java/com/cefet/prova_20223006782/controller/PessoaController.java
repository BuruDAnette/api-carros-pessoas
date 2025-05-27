package com.cefet.prova_20223006782.controller;

import com.cefet.prova_20223006782.dto.PessoaDTO;
import com.cefet.prova_20223006782.dto.CarroDTO;
import com.cefet.prova_20223006782.service.PessoaService;
import com.cefet.prova_20223006782.service.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<PessoaDTO> pessoaDTOs = pessoaService.findAll();
        return ResponseEntity.ok(pessoaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.findById(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO novaPessoa = pessoaService.insert(pessoaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO pessoaAtualizado = pessoaService.update(id, pessoaDTO);
        return ResponseEntity.ok(pessoaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build(); 
    }

    @GetMapping("/{id}/carros")
    public ResponseEntity<List<CarroDTO>> listarCarrosPorPessoa(@PathVariable Long id) {
        List<CarroDTO> carros = carroService.findByPessoaId(id);
        return ResponseEntity.ok(carros);
    }
}