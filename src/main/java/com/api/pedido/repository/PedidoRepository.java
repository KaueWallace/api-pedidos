package com.api.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
