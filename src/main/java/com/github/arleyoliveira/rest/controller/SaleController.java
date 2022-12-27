package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.rest.dto.SaleDTO;
import com.github.arleyoliveira.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/sales")
@ResponseBody
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sale save(@RequestBody SaleDTO dto) {
        return saleService.save(dto);
    }
}
