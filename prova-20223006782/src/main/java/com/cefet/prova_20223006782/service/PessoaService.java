package com.cefet.prova_20223006782.service;

import com.cefet.prova_20223006782.dto.PessoaDTO;
import com.cefet.prova_20223006782.dto.CarroDTO;
import com.cefet.prova_20223006782.model.Pessoa;
import com.cefet.prova_20223006782.model.Carro;
import com.cefet.prova_20223006782.repository.PessoaRepository;
import com.cefet.prova_20223006782.repository.CarroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;


@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CarroRepository carroRepository;

    // buscar pessoa
    public List<PessoaDTO> findAll(){
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();   
    }

    // buscar por ID
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));
        return new PessoaDTO(pessoa);
    }

    // inserir Pessoa
    public PessoaDTO insert(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        Pessoa pessoaSalvo = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaSalvo);
    }

    // atualizar Dados de Pessoa
    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(pessoaDTO.getCpf());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    // remove Pessoa pelo seu id
    public void delete(Long id) {
        System.out.println("Tentando deletar pessoa com ID: " + id); // Log adicional
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa não encontrado com ID: " + id);
        }
        pessoaRepository.deleteById(id);
        System.out.println("Pessoa deletada com sucesso"); 
    }

    // buscar e listar carros de uma pessoa pelo ID
    public List<CarroDTO> findCarrosByPessoaCpf(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Nenhuma pessoa encontrada com CPF: " + cpf));
        
        List<Carro> carros = carroRepository.findByPessoaId(pessoa.getId());
        if (carros.isEmpty()) {
            throw new EntityNotFoundException("Nenhum carro encontrado para o CPF: " + cpf);
        }
        return carros.stream().map(CarroDTO::new).toList();
    }
}