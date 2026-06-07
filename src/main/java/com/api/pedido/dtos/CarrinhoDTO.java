package com.api.pedido.dtos;

import java.util.Set;

public record CarrinhoDTO(

    Long id,

    Double total,

    Set<ItemCarrinhoDTO> itens

) {}