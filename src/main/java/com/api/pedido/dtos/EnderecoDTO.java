package com.api.pedido.dtos;

public record EnderecoDTO(
    Long id,
    String cep,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado
) {}
