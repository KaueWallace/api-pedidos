package com.api.pedido.dtos;

public record ItemPedidoDTO(
    String produto,
    Integer quantidade,
    Double precoUnitario,
    Double subTotal
) {}
