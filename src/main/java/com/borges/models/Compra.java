package com.borges.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Filial filial;
    @ManyToOne
    private Fornecedor fornecedor;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "compra_id")
    private List<ItemCompra> itens;
}
