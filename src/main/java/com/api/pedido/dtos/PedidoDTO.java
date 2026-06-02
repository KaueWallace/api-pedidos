package com.api.pedido.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import com.api.pedido.domain.enums.EstadoPedido;

public record PedidoDTO(
    Long id,
    LocalDateTime dataPedido,
    Double valorTotal,
    EstadoPedido status,
    Set<ItemPedidoDTO> itens,
    UsuarioResumoDTO usuario
) 
{}
