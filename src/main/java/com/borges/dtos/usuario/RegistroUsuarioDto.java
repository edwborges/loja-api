package com.borges.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroUsuarioDto {

    private String email;
    private String senha;
    private Long perfilId;
    private Long filialId;
}
