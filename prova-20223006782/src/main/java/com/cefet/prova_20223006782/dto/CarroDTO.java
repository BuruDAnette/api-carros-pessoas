package com.cefet.prova_20223006782.dto;

import com.cefet.prova_20223006782.entity.Carro;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarroDTO {
    private Long id;
    private String placa;
    private String nomePessoa;
    private String cpfPessoa;
    private Long idPessoa;
    private Double pontuacao;

    public CarroDTO(Carro carro) {
        this.id = carro.getId();
        this.placa = carro.getPlaca();
        this.nomePessoa = carro.getPessoa().getNome();
        this.cpfPessoa = carro.getPessoa().getCpf();
        this.idPessoa = carro.getPessoa().getId();
        this.pontuacao = carro.getPontuacao();
    }
}