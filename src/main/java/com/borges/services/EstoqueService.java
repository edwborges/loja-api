package com.borges.services;

import com.borges.models.Estoque;
import com.borges.models.Filial;
import com.borges.models.Produto;
import com.borges.repositories.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Integer acrescentaQuantidadeEstoque(Long produtoId, Long filialId, Integer quantidade) {
        Optional<Estoque> op_estoque = estoqueRepository.findByProduto_IdAndFilial_Id(produtoId, filialId);
        if(op_estoque.isPresent()) {
            Estoque estoque = op_estoque.get();
            estoque.setQuantidade(estoque.getQuantidade() + quantidade);
            return estoqueRepository.save(estoque).getQuantidade();
        }
        Estoque estoque = new Estoque();
        estoque.setProduto(new Produto(produtoId));
        estoque.setFilial(new Filial(filialId));
        estoque.setQuantidade(quantidade);
        return estoqueRepository.save(estoque).getQuantidade();
    }
}
