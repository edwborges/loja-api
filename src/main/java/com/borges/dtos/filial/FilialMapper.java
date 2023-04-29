package com.borges.dtos.filial;

import com.borges.dtos.endereco.EnderecoMapper;
import com.borges.models.Filial;

public class FilialMapper {

    public static Filial fromDto(RegistroFilialDto dto) {
        return new Filial(null, dto.getNome(), EnderecoMapper.fromDto(dto.getEndereco()));
    }

    public static ConsultarFilialDto fromEntity(Filial filial) {
        return new ConsultarFilialDto(filial.getId(),
                filial.getNome(), EnderecoMapper.fromEntity(filial.getEndereco()));
    }
}
