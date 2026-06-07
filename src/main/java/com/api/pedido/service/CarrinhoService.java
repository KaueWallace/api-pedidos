package com.api.pedido.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.Carrinho;
import com.api.pedido.domain.ItemCarrinho;
import com.api.pedido.domain.Produto;
import com.api.pedido.domain.Usuario;
import com.api.pedido.dtos.CarrinhoDTO;
import com.api.pedido.dtos.ItemCarrinhoDTO;
import com.api.pedido.dtos.ItemCarrinhoRequestDTO;
import com.api.pedido.repository.CarrinhoRepository;
import com.api.pedido.repository.ItemCarrinhoRepository;
import com.api.pedido.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    private Carrinho getCarrinhoUsuario() {

        Usuario usuario = getUsuarioLogado();

        return carrinhoRepository
                .findByUsuario(usuario)
                .orElseGet(() -> {

                    Carrinho carrinho = new Carrinho();

                    carrinho.setUsuario(usuario);

                    return carrinhoRepository.save(carrinho);

                });
    }

    @Transactional
    public void adicionarItem(ItemCarrinhoRequestDTO dto) {

        if (dto.quantidade() <= 0) {
            throw new RuntimeException(
                    "Quantidade inválida");
        }

        Carrinho carrinho = getCarrinhoUsuario();

        Produto produto = produtoRepository
                .findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException(
                        "Produto não encontrado"));

        Optional<ItemCarrinho> itemExistente = itemCarrinhoRepository
                .findByIdCarrinhoAndIdProduto(
                        carrinho,
                        produto);

        if (itemExistente.isPresent()) {

            ItemCarrinho item = itemExistente.get();

            item.setQuantidade(
                    item.getQuantidade()
                            + dto.quantidade());

            itemCarrinhoRepository.save(item);

            return;
        }

        ItemCarrinho item = new ItemCarrinho(
                carrinho,
                produto,
                dto.quantidade());

        itemCarrinhoRepository.save(item);
    }

    @Transactional
    public void removerItem(Long produtoId) {

        Carrinho carrinho = getCarrinhoUsuario();

        Produto produto = produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new RuntimeException(
                        "Produto não encontrado"));

        ItemCarrinho item = itemCarrinhoRepository
                .findByIdCarrinhoAndIdProduto(
                        carrinho,
                        produto)
                .orElseThrow(() -> new RuntimeException(
                        "Item não encontrado"));

        itemCarrinhoRepository.delete(item);
    }

    @Transactional
    public CarrinhoDTO buscarMeuCarrinho() {

        Carrinho carrinho = getCarrinhoUsuario();

        return converterDTO(carrinho);
    }

    @Transactional
    public void limparCarrinho() {

        Carrinho carrinho = getCarrinhoUsuario();

        carrinho.getItens().clear();

        carrinhoRepository.save(carrinho);
    }

    @Transactional
    public void atualizarQuantidade(
            Long produtoId,
            Integer quantidade) {

        Carrinho carrinho = getCarrinhoUsuario();

        Produto produto = produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new RuntimeException(
                        "Produto não encontrado"));

        ItemCarrinho item = itemCarrinhoRepository
                .findByIdCarrinhoAndIdProduto(
                        carrinho,
                        produto)
                .orElseThrow(() -> new RuntimeException(
                        "Item não encontrado"));

        item.setQuantidade(quantidade);

        itemCarrinhoRepository.save(item);
    }

    private CarrinhoDTO converterDTO(
            Carrinho carrinho) {

        return new CarrinhoDTO(
                carrinho.getId(),

                carrinho.getValorTotal(),

                carrinho.getItens()
                        .stream()
                        .map(this::converterDTO)
                        .collect(Collectors.toSet()));
    }

    private ItemCarrinhoDTO converterDTO(
            ItemCarrinho item) {

        return new ItemCarrinhoDTO(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getProduto().getPreco(),
                item.getQuantidade(),
                item.getSubTotal());
    }

    private Usuario getUsuarioLogado() {

        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}