package com.api.pedido.dtos;

public record EnderecoResumoDTO(
    String cep,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado,
    UsuarioResumoDTO usuario
) {}
