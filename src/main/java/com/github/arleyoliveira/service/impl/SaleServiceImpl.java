package com.github.arleyoliveira.service.impl;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Item;
import com.github.arleyoliveira.domain.entities.Product;
import com.github.arleyoliveira.domain.entities.Sale;
import com.github.arleyoliveira.domain.enums.SaleStatus;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import com.github.arleyoliveira.domain.repositories.ItemRepository;
import com.github.arleyoliveira.domain.repositories.ProductRepository;
import com.github.arleyoliveira.domain.repositories.SaleRepository;
import com.github.arleyoliveira.exception.ClientNotFoundException;
import com.github.arleyoliveira.exception.ProductNotFoundException;
import com.github.arleyoliveira.exception.SaleNotFoundException;
import com.github.arleyoliveira.exception.SaleWithoutItemException;
import com.github.arleyoliveira.rest.dto.ItemRequestDTO;
import com.github.arleyoliveira.rest.dto.SaleRequestDTO;
import com.github.arleyoliveira.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    public Sale save(SaleRequestDTO dto) {

        Customer customer = customerRepository
                .findById(dto.getCustomer())
                .orElseThrow(() -> new ClientNotFoundException("Customer invalid!"));

        Sale sale = new Sale();
        sale.setTotal(dto.getTotal());
        sale.setCreated(LocalDate.now());
        sale.setCustomer(customer);
        sale.setStatus(SaleStatus.CONFIRMED);

        Set<Item> items = convertItems(sale, dto.getItems());

        saleRepository.save(sale);

        itemRepository.saveAll(items);

        sale.setItems(items);

        return sale;
    }

    @Override
    public Optional<Sale> getById(Integer id) {
        return saleRepository.findByIdFetchItems(id);
    }

    @Override
    @Transactional
    public void updateStatus(Integer id, SaleStatus newStatus) {
        saleRepository
                .findById(id)
                .map(sale -> {
                    sale.setStatus(newStatus);
                    return saleRepository.save(sale);
                })
                .orElseThrow(() -> new SaleNotFoundException("Sale not found!"));
    }

    private Set<Item> convertItems(Sale sale, List<ItemRequestDTO> itemsDto) {
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
