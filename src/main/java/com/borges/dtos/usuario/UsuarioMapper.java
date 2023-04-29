package com.borges.dtos.usuario;

import com.borges.dtos.filial.FilialMapper;
import com.borges.models.Filial;
import com.borges.models.Perfil;
import com.borges.models.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioMapper {

    public static Usuario fromDto(RegistroUsuarioDto dto) {
        Perfil perfil = new Perfil();
        perfil.setId(dto.getPerfilId());
        Filial filial = new Filial();
        filial.setId(dto.getFilialId());
        return new Usuario(null, dto.getEmail(), new BCryptPasswordEncoder().encode(dto.getSenha()), perfil, filial);
    }

    public static ConsultaUsuarioDto fromEntity(Usuario usuario) {
        return new ConsultaUsuarioDto(usuario.getEmail(), usuario.getPerfil().getNome(),
                FilialMapper.fromEntity(usuario.getFilial()));
    }
}
