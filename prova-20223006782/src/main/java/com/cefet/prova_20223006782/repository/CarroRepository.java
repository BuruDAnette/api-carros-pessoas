package com.cefet.prova_20223006782.repository;

import com.cefet.prova_20223006782.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long>{
    boolean existsByPlaca(String placa);
    List<Carro> findByPessoaId(Long pessoaId);
}
