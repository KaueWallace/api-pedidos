package com.api.pedido.dtos;

public record ItemCarrinhoRequestDTO(
    Long produtoId,
    Integer quantidade
) {}
