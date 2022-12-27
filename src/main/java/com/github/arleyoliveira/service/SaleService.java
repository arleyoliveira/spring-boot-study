package com.github.arleyoliveira.service;

import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.domain.enums.SaleStatus;
import com.github.arleyoliveira.rest.dto.SaleRequestDTO;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.util.Optional;

public interface SaleService {
    public Sale save(SaleRequestDTO dto);
    public Optional<Sale> getById(Integer id);

    public void updateStatus(Integer id, SaleStatus newStatus);
}
