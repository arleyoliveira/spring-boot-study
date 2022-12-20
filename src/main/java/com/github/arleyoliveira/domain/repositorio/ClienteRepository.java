package com.github.arleyoliveira.domain.repositorio;

import com.github.arleyoliveira.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    @Query("SELECT c FROM Cliente c WHERE c.nome like %:nome%")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    List<Cliente> findByNomeContaining(String nome);

    boolean existsByNome(String nome);
}
