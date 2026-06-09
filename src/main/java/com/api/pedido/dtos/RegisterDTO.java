package com.api.pedido.dtos;

public record RegisterDTO(
    String email,
    String senha,
    String nome
) {

}
