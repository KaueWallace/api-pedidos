package com.api.pedido.dtos;

public record EnderecoCadastroDTO(
    String cep,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado
) {}
