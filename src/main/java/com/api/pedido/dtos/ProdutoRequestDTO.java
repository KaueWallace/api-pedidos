package com.api.pedido.dtos;

public record ProdutoRequestDTO(

    String nome,

    Double preco,

    Integer estoque,

    String imagem

) {}