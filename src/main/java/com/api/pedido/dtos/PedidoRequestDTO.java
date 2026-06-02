package com.api.pedido.dtos;

import java.util.Set;

public record PedidoRequestDTO(
    Set<ItemPedidoRequestDTO> itens,
    Long enderecoId
) {}
