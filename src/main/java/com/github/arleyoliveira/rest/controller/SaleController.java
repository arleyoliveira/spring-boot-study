package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Item;
import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.domain.enums.SaleStatus;
import com.github.arleyoliveira.exception.ProductNotFoundException;
import com.github.arleyoliveira.rest.dto.ItemResponseDTO;
import com.github.arleyoliveira.rest.dto.SaleRequestDTO;
import com.github.arleyoliveira.rest.dto.SaleResponseDTO;
import com.github.arleyoliveira.rest.dto.UpdateSaleStatusDTO;
import com.github.arleyoliveira.service.SaleService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("api/sales")
@Api(tags = "Sales")
public class SaleController {

    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sale save(@RequestBody @Valid SaleRequestDTO dto) {
        return saleService.save(dto);
    }

    @GetMapping("/{id}")
    public SaleResponseDTO getById(@PathVariable Integer id) {
        return saleService
                .getById(id)
                .map(this::convert)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));
    }

    private SaleResponseDTO convert(Sale sale) {
        return SaleResponseDTO.builder()
                .id(sale.getId())
                .created(sale.getCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(sale.getCustomer().getCpf())
                .customer(sale.getCustomer().getName())
                .total(sale.getTotal())
                .status(sale.getStatus().name())
                .items(convertItems(sale.getItems()))
                .build();
    }

    private List<ItemResponseDTO> convertItems(Set<Item> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        }

        return items.stream().map(item -> ItemResponseDTO
                .builder()
                .description(item.getProduct().getDescription())
                .unitaryValue(item.getUnitaryValue())
                .total(item.getTotal())
                .amount(item.getAmount())
                .build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable("id") Integer id,  @RequestBody UpdateSaleStatusDTO dto) {
        saleService.updateStatus(id, SaleStatus.valueOf(dto.getNewStatus()));
    }
}
