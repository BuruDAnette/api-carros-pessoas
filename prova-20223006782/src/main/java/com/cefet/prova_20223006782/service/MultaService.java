package com.cefet.prova_20223006782.service;

import com.cefet.prova_20223006782.dto.MultaDTO;
import com.cefet.prova_20223006782.entity.Carro;
import com.cefet.prova_20223006782.entity.Multa;
import com.cefet.prova_20223006782.repository.CarroRepository;
import com.cefet.prova_20223006782.repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class MultaService {
    @Autowired
    private MultaRepository multaRepository;
    
    @Autowired
    private CarroRepository carroRepository;

    public List<MultaDTO> findAll() {
        return multaRepository.findAll().stream().map(MultaDTO::new).toList();
    }

    public MultaDTO findById(Long id) {
        Multa multa = multaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Multa não encontrada com ID: " + id));
        return new MultaDTO(multa);
    }

    public List<MultaDTO> findByCarroId(Long carroId) {
        return multaRepository.findByCarroId(carroId).stream().map(MultaDTO::new).toList();
    }

    @Transactional
    public MultaDTO insert(MultaDTO multaDTO) {
        Carro carro = carroRepository.findById(multaDTO.getCarroId())
            .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado com ID: " + multaDTO.getCarroId()));

        Multa multa = new Multa();
        multa.setPontos(multaDTO.getPontos());
        multa.setCarro(carro);

        carro.setPontuacao(carro.getPontuacao() + multaDTO.getPontos());
        carroRepository.save(carro);

        return new MultaDTO(multaRepository.save(multa));
    }

    @Transactional
    public void delete(Long id) {
        Multa multa = multaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Multa não encontrada com ID: " + id));

        Carro carro = multa.getCarro();
        carro.setPontuacao(carro.getPontuacao() - multa.getPontos());
        carroRepository.save(carro);

        multaRepository.delete(multa);
    }

    @Transactional
    public MultaDTO update(Long id, MultaDTO multaDTO) {
        Multa multa = multaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Multa não encontrada com ID: " + id));

        double diferencaPontos = multaDTO.getPontos() - multa.getPontos();

        Carro carro = multa.getCarro();
        carro.setPontuacao(carro.getPontuacao() + diferencaPontos);
        carroRepository.save(carro);

        multa.setPontos(multaDTO.getPontos());
        return new MultaDTO(multaRepository.save(multa));
    }
}

