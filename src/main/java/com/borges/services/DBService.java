package com.borges.services;

import com.borges.models.Endereco;
import com.borges.models.Filial;
import com.borges.models.Perfil;
import com.borges.models.Usuario;
import com.borges.repositories.FilialRepository;
import com.borges.repositories.PerfilRepository;
import com.borges.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DBService {

   	private final FilialRepository filialRepository;
	private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public DBService(FilialRepository filialRepository, UsuarioRepository usuarioRepository, AutenticacaoService autenticacaoService, PerfilRepository perfilRepository) {
        this.filialRepository = filialRepository;
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    public void run(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Perfil perfil = new Perfil();
        Endereco endereco = new Endereco();
        Filial filial = new Filial();
        Usuario usuario = new Usuario();

        perfil.setId(1L);
        perfil.setNome("ADMIN");

        endereco.setLogradouro("praça");
        endereco.setNumero("1234");
        endereco.setComplemento("casa");
        endereco.setCep("12345678");

        filial.setId(1L);
        filial.setNome("Filial 1");
        filial.setEndereco(endereco);

        usuario.setId(3L);
        usuario.setEmail("admin@admin");
        usuario.setSenha(encoder.encode("1234"));
        usuario.setPerfil(perfil);
        usuario.setFilial(filial);

        perfilRepository.save(perfil);
        filialRepository.save(filial);
        usuarioRepository.save(usuario);

	}
}
