package com.api.pedido.dtos;

public record LoginResponseDTO(
    String token,
    String role
) {

}
