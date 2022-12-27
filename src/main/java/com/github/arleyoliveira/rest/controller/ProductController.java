package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Product;
import com.github.arleyoliveira.domain.repositories.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@ResponseBody
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable Integer id) {
        return productRepository.findById(id).map(product -> product)
                .orElseThrow(this::notFound);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> find(Product filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> example = Example.of(filter, matcher);
        return productRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Product product) {
        productRepository
                .findById(id)
                .map(productOriginal -> {
                    product.setId(productOriginal.getId());
                    productRepository.save(product);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(this::notFound);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        productRepository
                .findById(id)
                .map(customerOriginal -> {
                    productRepository.delete(customerOriginal);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(this::notFound);
    }

    public ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
}
