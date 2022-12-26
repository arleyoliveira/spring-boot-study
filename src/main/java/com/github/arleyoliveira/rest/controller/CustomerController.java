package com.github.arleyoliveira.rest.controller;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(@Autowired CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (!customer.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer.get());
    }

}
