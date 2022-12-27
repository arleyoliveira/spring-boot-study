package com.github.arleyoliveira.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Integer product;
    private Integer amount;
}
