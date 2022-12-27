package com.github.arleyoliveira.rest.dto;

import com.github.arleyoliveira.validations.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequestDTO {

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer customer;

    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    private List<ItemRequestDTO> items;
}
