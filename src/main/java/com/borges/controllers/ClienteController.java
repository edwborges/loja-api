package com.borges.controllers;

import com.borges.dtos.cliente.ClienteMapper;
import com.borges.dtos.cliente.ConsultarClienteDto;
import com.borges.dtos.cliente.RegistroClienteDto;
import com.borges.models.Cliente;
import com.borges.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ApiOperation(value = "Buscar todos os cientes.")
    public ResponseEntity<Page<ConsultarClienteDto>> buscartTodosOsClientes(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(clienteService.buscarTodosOsClientes(pageable)
                .map(ClienteMapper::fromEntity));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar um cliente.")
    public ResponseEntity<ConsultarClienteDto> salvarCliente(@RequestBody RegistroClienteDto dto) {
        Cliente cliente = clienteService.salvarCliente(ClienteMapper.fromDto(dto));
        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Buscar um cliente.")
    public ResponseEntity<ConsultarClienteDto> buscarCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarCliente(id);
        return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Atualizar um cliente.")
    public ResponseEntity<ConsultarClienteDto> alterarCliente(@RequestBody RegistroClienteDto dto,
                                                              @PathVariable Long id) {
        try {
            Cliente cliente = clienteService.atualizarCliente(ClienteMapper.fromDto(dto), id);
            return ResponseEntity.ok(ClienteMapper.fromEntity(cliente));
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Excluir um cliente.")
    public ResponseEntity<ConsultarClienteDto> excluirCliente(@PathVariable Long id) {
        try {
            clienteService.excluirCliente(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
