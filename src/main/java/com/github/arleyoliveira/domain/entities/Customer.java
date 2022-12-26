package com.github.arleyoliveira.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Sale> sales;

    public Customer() {}

    public Customer(String nome) {
        this.name = nome;
    }

    public Integer getId() {
        return id;
    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
