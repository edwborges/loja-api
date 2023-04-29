package com.borges.controllers;

import com.borges.dtos.usuario.ConsultaUsuarioDto;
import com.borges.dtos.usuario.RegistroUsuarioDto;
import com.borges.dtos.usuario.UsuarioMapper;
import com.borges.models.Usuario;
import com.borges.services.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar um usuario.")
    public ResponseEntity<ConsultaUsuarioDto> salvarUsuario(@RequestBody RegistroUsuarioDto dto) {
        Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDto(dto));
        return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
    }
}
