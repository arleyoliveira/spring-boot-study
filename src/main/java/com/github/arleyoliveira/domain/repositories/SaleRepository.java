package com.github.arleyoliveira.domain.repositories;

import com.github.arleyoliveira.domain.entities.Customer;
import com.github.arleyoliveira.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Set<Sale> findByCustomer(Customer customer);

    @Query(" SELECT s FROM Sale s LEFT JOIN FETCH s.items WHERE s.id = :id ")
    Optional<Sale> findByIdFetchItems(@Param("id") Integer id);
}
