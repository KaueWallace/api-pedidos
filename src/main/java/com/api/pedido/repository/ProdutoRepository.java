package com.api.pedido.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
