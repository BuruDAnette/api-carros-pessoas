package com.cefet.prova_20223006782.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_multa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double pontos;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;
}

