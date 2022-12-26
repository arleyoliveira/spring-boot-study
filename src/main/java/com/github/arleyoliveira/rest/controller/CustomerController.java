package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/customers")
@ResponseBody
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer.get());
    }

    @GetMapping
    public ResponseEntity find(Customer filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> example = Example.of(filter, matcher);
        List<Customer> customers = customerRepository.findAll(example);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Customer customer) {
        return customerRepository
                .findById(id)
                .map(customerOriginal -> {
                    customer.setId(customerOriginal.getId());
                    customerRepository.save(customer);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> {
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        customerRepository.delete(customer.get());

        return ResponseEntity.noContent().build();
    }
}
