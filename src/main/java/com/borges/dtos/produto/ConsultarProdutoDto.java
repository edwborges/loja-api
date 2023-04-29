package com.borges.dtos.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultarProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private String unidade;
}
