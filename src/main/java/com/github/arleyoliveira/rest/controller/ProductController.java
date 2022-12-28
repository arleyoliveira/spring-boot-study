package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Product;
import com.github.arleyoliveira.domain.repositories.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "Products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ApiOperation("Get Product By Id")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable @ApiParam Integer id) {
        return productRepository.findById(id).map(product -> product)
                .orElseThrow(this::notFound);
    }

    @ApiOperation("Get All Products By Filter")
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

    @ApiOperation("Create Product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @ApiOperation("Update Product")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable @ApiParam Integer id, @RequestBody Product product) {
        productRepository
                .findById(id)
                .map(productOriginal -> {
                    product.setId(productOriginal.getId());
                    productRepository.save(product);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(this::notFound);
    }

    @ApiOperation("Delete Product By Id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @ApiParam Integer id) {
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
