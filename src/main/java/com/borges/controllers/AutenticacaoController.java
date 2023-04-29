package com.borges.controllers;

import com.borges.dtos.auth.TokenDto;
import com.borges.dtos.auth.AutenticacaoDto;
import com.borges.services.AutenticacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    @ApiOperation(value = "Autenticar um token.")
    public ResponseEntity<TokenDto> autenticar(@RequestBody AutenticacaoDto authForm) {
        try {
            return ResponseEntity.ok(autenticacaoService.autenticar(authForm));
        } catch(AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
