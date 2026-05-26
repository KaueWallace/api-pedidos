package com.api.pedido.dtos;

import java.time.LocalDateTime;
import java.util.Set;

public record PedidoDTO(
    Long id,
    LocalDateTime dataPedido,
    Double valorTotal,
    Set<ItemPedidoDTO> itens
) 
{}
