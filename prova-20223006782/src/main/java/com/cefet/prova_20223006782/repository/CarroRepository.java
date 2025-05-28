package com.cefet.prova_20223006782.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.prova_20223006782.entity.Carro;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long>{
    boolean existsByPlaca(String placa);
    List<Carro> findByPessoaId(Long pessoaId);
}
