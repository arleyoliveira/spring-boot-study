package com.github.arleyoliveira.domain.repositories;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Set<Sale> findByCustomer(Customer customer);
}
