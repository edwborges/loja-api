package com.borges.dtos.produto;

import com.borges.models.Produto;

public class ProdutoMapper {

    public static Produto fromDto(RegistroProdutoDto dto) {
        return new Produto(null, dto.getNome(), dto.getDescricao(), dto.getUnidade());
    }

    public static ConsultarProdutoDto fromEntity(Produto produto) {
        return new ConsultarProdutoDto(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getUnidade());
    }
}
