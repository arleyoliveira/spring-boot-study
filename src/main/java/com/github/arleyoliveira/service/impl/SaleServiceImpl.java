package com.github.arleyoliveira.service.impl;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Item;
import com.github.arleyoliveira.domain.entities.Product;
import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import com.github.arleyoliveira.domain.repositories.ItemRepository;
import com.github.arleyoliveira.domain.repositories.ProductRepository;
import com.github.arleyoliveira.domain.repositories.SaleRepository;
import com.github.arleyoliveira.exception.ClientNotFoundException;
import com.github.arleyoliveira.exception.ProductNotFoundException;
import com.github.arleyoliveira.exception.SaleWithoutItemException;
import com.github.arleyoliveira.rest.dto.ItemDTO;
import com.github.arleyoliveira.rest.dto.SaleDTO;
import com.github.arleyoliveira.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Sale save(SaleDTO dto) {

        Customer customer = customerRepository
                .findById(dto.getCustomer())
                .orElseThrow(() -> new ClientNotFoundException("Customer invalid!"));

        Sale sale = new Sale();
        sale.setTotal(dto.getTotal());
        sale.setCreated(LocalDate.now());
        sale.setCustomer(customer);

        Set<Item> items = convertItems(sale, dto.getItems());

        saleRepository.save(sale);

        itemRepository.saveAll(items);

        sale.setItems(items);

        return sale;
    }

    private Set<Item> convertItems(Sale sale, List<ItemDTO> itemsDto) {
        if (itemsDto.isEmpty()) {
            throw new SaleWithoutItemException("Sale without item!");
        }

        return itemsDto
                .stream()
                .map(itemDTO -> {

                    Product product = productRepository
                            .findById(itemDTO.getProduct())
                            .orElseThrow(() -> new ProductNotFoundException(String.format("Product %d not found", itemDTO.getProduct())));

                    Item item = new Item();
                    item.setSale(sale);
                    item.setAmount(itemDTO.getAmount());
                    item.setProduct(product);

                    item.calculateTotal();
                    return item;
                }).collect(Collectors.toSet());
    }
}
