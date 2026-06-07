package com.api.pedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Carrinho;
import com.api.pedido.domain.Usuario;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario(Usuario usuario);
}
