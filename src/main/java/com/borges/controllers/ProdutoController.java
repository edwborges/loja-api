package com.borges.controllers;

import com.borges.dtos.produto.ConsultarProdutoDto;
import com.borges.dtos.produto.ProdutoMapper;
import com.borges.dtos.produto.RegistroProdutoDto;
import com.borges.models.Produto;
import com.borges.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @ApiOperation(value = "Buscar todos os produtos.")
    public ResponseEntity<Page<ConsultarProdutoDto>> buscarTodosOsProdutos(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(produtoService.buscarTodosOsProduto(pageable)
                .map(ProdutoMapper::fromEntity));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar um produto.")
    public ResponseEntity<ConsultarProdutoDto> salvarProduto(@RequestBody RegistroProdutoDto dto) {
        Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDto(dto));
        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Buscar um produto.")
    public ResponseEntity<ConsultarProdutoDto> buscarProduto(@PathVariable Long id) {
        Produto produto = produtoService.buscarProduto(id);
        return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Atualizar um produto.")
    public ResponseEntity<ConsultarProdutoDto> alterarProduto(@RequestBody RegistroProdutoDto dto,
                                                              @PathVariable Long id) {
        try {
            Produto produto = produtoService.atualizarProduto(ProdutoMapper.fromDto(dto), id);
            return ResponseEntity.ok(ProdutoMapper.fromEntity(produto));
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Excluir um produto.")
    public ResponseEntity<ConsultarProdutoDto> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
