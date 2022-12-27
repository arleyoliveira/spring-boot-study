package com.github.arleyoliveira.service;

import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.rest.dto.SaleDTO;

public interface SaleService {
    public Sale save(SaleDTO dto);
}
