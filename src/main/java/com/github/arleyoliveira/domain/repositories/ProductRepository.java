package com.github.arleyoliveira.domain.repositories;

import com.github.arleyoliveira.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
