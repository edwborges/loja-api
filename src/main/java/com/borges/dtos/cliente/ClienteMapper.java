package com.borges.dtos.cliente;

import com.borges.dtos.endereco.EnderecoMapper;
import com.borges.models.Cliente;

public class ClienteMapper {

    public static Cliente fromDto(RegistroClienteDto dto) {
        return new Cliente(null, dto.getCpf(), dto.getNome(), dto.getTelefone(), dto.getEmail(),
                EnderecoMapper.fromDto(dto.getEndereco()));
    }

    public static ConsultarClienteDto fromEntity(Cliente cliente) {
        return new ConsultarClienteDto(cliente.getId(), cliente.getCpf(), cliente.getNome(),
                cliente.getTelefone(), cliente.getEmail(), EnderecoMapper.fromEntity(cliente.getEndereco()));
    }
}
