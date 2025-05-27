package com.cefet.prova_20223006782.service;

import com.cefet.prova_20223006782.dto.CarroDTO;
import com.cefet.prova_20223006782.model.Carro;
import com.cefet.prova_20223006782.model.Pessoa;
import com.cefet.prova_20223006782.repository.CarroRepository;
import com.cefet.prova_20223006782.repository.PessoaRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;
    // private String id;

    // Buscar todos
    public List<CarroDTO> findAll(){
        List<Carro> listaCarro = carroRepository.findAll();
        return listaCarro.stream().map(CarroDTO::new).toList();
    }

    // Buscar por ID
    public CarroDTO findById(Long id) {
        Carro carro = carroRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com ID: " + id));
        return new CarroDTO(carro);
    }

    public List<CarroDTO> findByPessoaId(Long pessoaId) {
        List<Carro> carros = carroRepository.findByPessoaId(pessoaId);
        return carros.stream().map(CarroDTO::new).toList();
    }

    //cadastrar novo carro
    public CarroDTO insert(CarroDTO carroDTO) {
        System.out.println("DTO recebido: " + carroDTO); // Log simples
        if (carroDTO.getPlaca() == null || carroDTO.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa é obrigatória");
        }
        if (carroDTO.getCpfPessoa() == null || carroDTO.getCpfPessoa().isEmpty()) {
            throw new IllegalArgumentException("CPF da pessoa é obrigatório");
        }
        
        // Validação do formato do CPF
        String cpf = carroDTO.getCpfPessoa();
        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        
        Pessoa pessoa = pessoaRepository.findByCpf(carroDTO.getCpfPessoa())
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com CPF: " + carroDTO.getCpfPessoa()));
        
        if (carroRepository.existsByPlaca(carroDTO.getPlaca())) {
            throw new IllegalArgumentException("Placa já cadastrada: " + carroDTO.getPlaca());
        }
        
        Carro carro = new Carro();
        carro.setPlaca(carroDTO.getPlaca());
        carro.setPessoa(pessoa);
        
        return new CarroDTO(carroRepository.save(carro));
    }

    public CarroDTO update(Long id, CarroDTO carroDTO) {
    Carro carro = carroRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com ID: " + id));
    if (!carro.getPlaca().equals(carroDTO.getPlaca())) {
        if (carroRepository.existsByPlaca(carroDTO.getPlaca())) {
            throw new IllegalArgumentException("Placa já cadastrada");
        }
        carro.setPlaca(carroDTO.getPlaca());
    }
    if (!carro.getPessoa().getCpf().equals(carroDTO.getCpfPessoa())) {
        Pessoa novaPessoa = pessoaRepository.findByCpf(carroDTO.getCpfPessoa())
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada com CPF: " + carroDTO.getCpfPessoa()));
        carro.setPessoa(novaPessoa);
    }

    Carro carroAtualizado = carroRepository.save(carro);
    return new CarroDTO(carroAtualizado);
    }

    //deletar carro
    public void delete(Long id) {
        Carro carro = carroRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com ID: " + id));
        carroRepository.delete(carro);
    }
}