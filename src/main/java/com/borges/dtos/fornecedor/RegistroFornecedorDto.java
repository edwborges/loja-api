package com.borges.dtos.fornecedor;

import com.borges.dtos.endereco.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroFornecedorDto {

    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private EnderecoDto endereco;
}
