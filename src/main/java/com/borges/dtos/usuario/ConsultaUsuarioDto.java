package com.borges.dtos.usuario;

import com.borges.dtos.filial.ConsultarFilialDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaUsuarioDto {

    private String email;
    private String nomePerfil;
    private ConsultarFilialDto filial;
}
