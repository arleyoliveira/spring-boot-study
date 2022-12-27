package com.github.arleyoliveira.domain.repositories;

import com.github.arleyoliveira.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
