package com.api.pedido.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
