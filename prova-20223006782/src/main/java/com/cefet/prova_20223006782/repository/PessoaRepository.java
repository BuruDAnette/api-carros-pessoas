package com.cefet.prova_20223006782.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.prova_20223006782.entity.Pessoa;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    Optional<Pessoa> findByCpf(String cpf);
}
