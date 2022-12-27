package com.github.arleyoliveira.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleResponseDTO {
    private Integer id;
    private String created;
    private String cpf;
    private String customer;
    private BigDecimal total;
    private String status;
    private List<ItemResponseDTO> items;
}
