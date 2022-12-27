package com.github.arleyoliveira.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Integer customer;
    private BigDecimal total;
    private List<ItemDTO> items;
}
