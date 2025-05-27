package com.cefet.prova_20223006782.dto;

import com.cefet.prova_20223006782.model.Pessoa;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    
    public PessoaDTO(Pessoa pessoa){
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
    }
}