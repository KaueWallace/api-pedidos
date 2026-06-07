package com.api.pedido.dtos;

public record ItemCarrinhoDTO(
    Long produtoId,
    String produto,
    Double precoUnitario,
    Integer quantidade,
    Double subTotal
) {}