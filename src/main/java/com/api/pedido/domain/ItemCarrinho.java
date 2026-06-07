package com.api.pedido.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinho {

    @EmbeddedId
    private ItemCarrinhoPK id = new ItemCarrinhoPK();

    private Integer quantidade;

    public Double getSubTotal() {
        return id.getProduto().getPreco() * quantidade;
    }

    public ItemCarrinho(Carrinho carrinho, Produto produto, Integer quantidade){
        id.setCarrinho(carrinho);
        id.setProduto(produto);
        this.quantidade = quantidade;
    }

    public Carrinho getCarrinho(){
        return id.getCarrinho();
    }

    public Produto getProduto(){
        return id.getProduto();
    }
}
