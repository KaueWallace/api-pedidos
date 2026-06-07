package com.api.pedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Carrinho;
import com.api.pedido.domain.ItemCarrinho;
import com.api.pedido.domain.Produto;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long>{
    Optional<ItemCarrinho> findByIdCarrinhoAndIdProduto(
            Carrinho carrinho,
            Produto produto);
}
