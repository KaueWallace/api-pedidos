package com.api.pedido.dtos;

public record ProdutoDTO(
    Long id,
    String nome,
    Double preco,
    Integer estoque,
    String imagem
) {}
