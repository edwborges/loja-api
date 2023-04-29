package com.borges.services;

import com.borges.exception.EntityNotFoundException;
import com.borges.models.Produto;
import com.borges.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Page<Produto> buscarTodosOsProduto(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto buscarProduto(Long id) {
        Optional<Produto> optional = produtoRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    }

    public Produto atualizarProduto(Produto produto, Long id) {
        Produto produtoOriginal = this.buscarProduto(id);
        produto.setId(produtoOriginal.getId());
        return produtoRepository.save(produto);
    }

    public void excluirProduto(Long id) {
        Produto produtoOriginal = this.buscarProduto(id);
        produtoRepository.delete(produtoOriginal);
    }
}
