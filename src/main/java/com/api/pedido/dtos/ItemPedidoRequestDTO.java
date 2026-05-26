package com.api.pedido.dtos;

public record ItemPedidoRequestDTO(
    Long produtoId,
    Integer quantidade
) {}
