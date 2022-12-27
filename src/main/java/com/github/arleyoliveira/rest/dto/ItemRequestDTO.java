package com.github.arleyoliveira.rest.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDTO {
    private Integer product;
    private Integer amount;
}
