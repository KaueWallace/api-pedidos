package com.api.pedido.dtos;

import com.api.pedido.domain.enums.EstadoPedido;

public record AtualizarStatusDTO(
    EstadoPedido status
) {}
