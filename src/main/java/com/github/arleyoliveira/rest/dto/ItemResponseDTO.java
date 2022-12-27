package com.github.arleyoliveira.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO {
    private String description;
    private Integer amount;
    private BigDecimal unitaryValue;
    private BigDecimal total;
}
