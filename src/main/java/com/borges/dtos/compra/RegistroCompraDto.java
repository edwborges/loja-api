package com.borges.dtos.compra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroCompraDto {

    private Long fornecedorId;
    private List<RegistroItemCompraDto> itens;
}
