package com.api.pedido.dtos;

import com.api.pedido.domain.enums.UserRole;

public record RegisterDTO(
    String email,
    String senha,
    UserRole role
) {

}
