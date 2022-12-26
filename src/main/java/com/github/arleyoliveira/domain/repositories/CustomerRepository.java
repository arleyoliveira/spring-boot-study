package com.github.arleyoliveira.domain.repositories;

import com.github.arleyoliveira.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.name like %:name%")
    List<Customer> findBySimilarName(@Param("name") String name);

    List<Customer> findByNameContaining(String name);

    boolean existsByName(String name);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.sales WHERE c.id = :id")
    Customer findCustomerFetchSales(@Param("id") Integer id);
}
