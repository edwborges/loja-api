package com.borges.dtos.cliente;

import com.borges.dtos.endereco.EnderecoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultarClienteDto {

    private Long id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private EnderecoDto endereco;
}
