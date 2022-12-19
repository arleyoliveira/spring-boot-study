package com.github.arleyoliveira.domain.repositorio;

import com.github.arleyoliveira.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome);

    boolean existsByNome(String nome);
}
