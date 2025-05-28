package com.cefet.prova_20223006782.service;

import com.cefet.prova_20223006782.dto.PessoaDTO;
import com.cefet.prova_20223006782.entity.Pessoa;
import com.cefet.prova_20223006782.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;


@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaDTO> findAll(){
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();   
    }

    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));
        return new PessoaDTO(pessoa);
    }

    public PessoaDTO insert(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        Pessoa pessoaSalvo = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaSalvo);
    }

    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    public void delete(Long id) {
        System.out.println("Tentando deletar pessoa com ID: " + id);
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrado com ID: " + id);
        }
        pessoaRepository.deleteById(id);
        System.out.println("Pessoa deletada com sucesso"); 
    }
}