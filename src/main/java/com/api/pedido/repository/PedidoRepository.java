package com.api.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Pedido;
import com.api.pedido.domain.Usuario;
import com.api.pedido.domain.enums.EstadoPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario(Usuario usuario);
    Optional<Pedido> findByIdAndUsuario(Long id, Usuario usuario);
    boolean existsByEnderecoId(Long enderecoId);
    List<Pedido> findByUsuarioAndStatus(Usuario usuario, EstadoPedido status);
}
