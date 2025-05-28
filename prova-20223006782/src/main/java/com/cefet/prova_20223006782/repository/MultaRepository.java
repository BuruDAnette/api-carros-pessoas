package com.cefet.prova_20223006782.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.prova_20223006782.entity.Multa;

import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    List<Multa> findByCarroId(Long carroId);
}