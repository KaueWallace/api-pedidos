package com.api.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
