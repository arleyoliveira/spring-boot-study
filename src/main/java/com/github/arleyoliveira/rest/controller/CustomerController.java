package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/customers")
@ResponseBody
@Api(tags = "Customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getById(@PathVariable Integer id) {
        return customerRepository.findById(id).map(customer -> customer)
                .orElseThrow(this::notFound);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> find(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> example = Example.of(filter, matcher);
        return customerRepository.findAll(example);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Customer customer) {
        customerRepository
                .findById(id)
                .map(customerOriginal -> {
                    customer.setId(customerOriginal.getId());
                    customerRepository.save(customer);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(this::notFound);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        customerRepository
                .findById(id)
                .map(customerOriginal -> {
                    customerRepository.delete(customerOriginal);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(this::notFound);
    }

    public ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}
