package com.borges.dtos.endereco;

import com.borges.models.Endereco;

public class EnderecoMapper {

    public static Endereco fromDto(EnderecoDto dto) {
        return new Endereco(dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getCep());
    }

    public static EnderecoDto fromEntity(Endereco endereco) {
        return new EnderecoDto(endereco.getLogradouro(), endereco.getNumero(),
                endereco.getComplemento(), endereco.getCep());
    }
}
