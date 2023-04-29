package com.borges.dtos.compra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroItemCompraDto {

    private Long produtoId;
    private Integer quantidade;
    private BigDecimal valor;
}
