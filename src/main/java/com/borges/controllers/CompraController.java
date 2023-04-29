package com.borges.controllers;

import com.borges.dtos.compra.RegistroCompraDto;
import com.borges.models.Compra;
import com.borges.models.Fornecedor;
import com.borges.models.ItemCompra;
import com.borges.models.Produto;
import com.borges.services.CompraService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar compra.")
    public Compra salvarCompra(@RequestBody RegistroCompraDto dto) {
        List<ItemCompra> listaItens = dto.getItens()
                .stream()
                .map(i -> new ItemCompra(null, new Produto(i.getProdutoId()), i.getQuantidade(), i.getValor()))
                .collect(Collectors.toList());

        Compra compra = new Compra(null, null, new Fornecedor(dto.getFornecedorId()), listaItens);
        return compraService.salvarCompra(compra);
    }
}
