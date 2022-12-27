package com.github.arleyoliveira.domain.entities;

import com.github.arleyoliveira.domain.enums.SaleStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created")
    private LocalDate created;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "sale")
    private Set<Item> items;

    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", created=" + created +
                ", total=" + total +
                '}';
    }
}
