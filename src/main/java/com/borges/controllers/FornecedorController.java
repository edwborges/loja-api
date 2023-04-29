package com.borges.controllers;

import com.borges.dtos.fornecedor.ConsultarFornecedorDto;
import com.borges.dtos.fornecedor.FornecedorMapper;
import com.borges.dtos.fornecedor.RegistroFornecedorDto;
import com.borges.models.Fornecedor;
import com.borges.services.FornecedorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    @ApiOperation(value = "Buscar todos os fornecedores.")
    public ResponseEntity<Page<ConsultarFornecedorDto>> buscarTodosOsFornecedores(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(fornecedorService.buscarTodosOsFornecedores(pageable)
                .map(FornecedorMapper::fromEntity));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar um fornecedor.")
    public ResponseEntity<ConsultarFornecedorDto> salvarFornecedor(@RequestBody RegistroFornecedorDto dto) {
        Fornecedor fornecedor = fornecedorService.salvarFornecedor(FornecedorMapper.fromDto(dto));
        return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Buscar um fornecedor.")
    public ResponseEntity<ConsultarFornecedorDto> buscarFornecedor(@PathVariable Long id) {
        Fornecedor fornecedor = fornecedorService.buscarFornecedor(id);
        return  ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Atualizar um fornecedor.")
    public ResponseEntity<ConsultarFornecedorDto> alterarFornecedor(@RequestBody RegistroFornecedorDto dto,
                                                                    @PathVariable Long id) {
        try {
            Fornecedor fornecedor = fornecedorService.atualizarFornecedor(FornecedorMapper.fromDto(dto), id);
            return ResponseEntity.ok(FornecedorMapper.fromEntity(fornecedor));
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Excluir um fornecedor.")
    public ResponseEntity<ConsultarFornecedorDto> excluirFornecedor(@PathVariable Long id) {
        try {
            fornecedorService.deletarFornecedor(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
