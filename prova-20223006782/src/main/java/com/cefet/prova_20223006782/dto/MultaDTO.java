package com.cefet.prova_20223006782.dto;

import com.cefet.prova_20223006782.entity.Multa;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultaDTO {
    private Long id;
    private Double pontos;
    private Long carroId;
    private String carroPlaca;

    public MultaDTO(Multa multa) {
        this.id = multa.getId();
        this.pontos = multa.getPontos();
        this.carroId = multa.getCarro().getId();
        this.carroPlaca = multa.getCarro().getPlaca();
    }
}