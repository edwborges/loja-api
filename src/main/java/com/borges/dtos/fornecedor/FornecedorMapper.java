package com.borges.dtos.fornecedor;

import com.borges.dtos.endereco.EnderecoMapper;
import com.borges.models.Fornecedor;

public class FornecedorMapper {

    public static Fornecedor fromDto(RegistroFornecedorDto dto) {
        return new Fornecedor(null, dto.getCnpj(), dto.getNome(), dto.getTelefone(), dto.getEmail(),
                EnderecoMapper.fromDto(dto.getEndereco()));
    }

    public static ConsultarFornecedorDto fromEntity(Fornecedor fornecedor) {
        return new ConsultarFornecedorDto(fornecedor.getId(), fornecedor.getCnpj(), fornecedor.getNome(),
                fornecedor.getTelefone(), fornecedor.getEmail(), EnderecoMapper.fromEntity(fornecedor.getEndereco()));
    }
}
