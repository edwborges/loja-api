package com.borges.repositories;

import com.borges.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProduto_IdAndFilial_Id(Long produtoId, Long filialId);
}
