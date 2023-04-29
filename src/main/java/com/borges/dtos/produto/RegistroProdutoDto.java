package com.borges.dtos.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroProdutoDto {

    private String nome;
    private String descricao;
    private String unidade;
}
