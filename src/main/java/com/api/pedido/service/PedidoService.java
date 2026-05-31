package com.api.pedido.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.ItemPedido;
import com.api.pedido.domain.Pedido;
import com.api.pedido.domain.Produto;
import com.api.pedido.domain.Usuario;
import com.api.pedido.domain.enums.EstadoPedido;
import com.api.pedido.dtos.AtualizarStatusDTO;
import com.api.pedido.dtos.ItemPedidoDTO;
import com.api.pedido.dtos.ItemPedidoRequestDTO;
import com.api.pedido.dtos.PedidoDTO;
import com.api.pedido.dtos.PedidoRequestDTO;
import com.api.pedido.repository.PedidoRepository;
import com.api.pedido.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<PedidoDTO> listar() {
        List<Pedido> lista = pedidoRepository.findAll();

        return lista.stream().map(this::converterDTO)
                .toList();
    }

    public List<PedidoDTO> listarMeusPedidos() {
        Usuario usuario = getUsuarioLogado();

        return pedidoRepository.findByUsuario(usuario)
                .stream()
                .map(this::converterDTO)
                .toList();
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();

        return converterDTO(pedido);
    }

    public PedidoDTO salvar(PedidoRequestDTO dto) {
        Usuario usuario = getUsuarioLogado();
        Pedido pedido = new Pedido();

        pedido.setUsuario(usuario);

        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(EstadoPedido.PENDENTE);

        Set<ItemPedido> itens = new HashSet<>();

        Double total = 0.0;

        for (ItemPedidoRequestDTO itemDTO : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

            ItemPedido item = new ItemPedido(
                    pedido,
                    produto,
                    itemDTO.quantidade(),
                    produto.getPreco());

            total += item.getSubTotal();

            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.setValorTotal(total);

        pedidoRepository.save(pedido);

        return converterDTO(pedido);
    }

    public void deletar(Long id) {
        pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedidoRepository.deleteById(id);
    }

    public PedidoDTO atualizarStatus(Long id, AtualizarStatusDTO dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(dto.status());

        pedidoRepository.save(pedido);

        return converterDTO(pedido);
    }

    private PedidoDTO converterDTO(Pedido pedido) {
        Set<ItemPedidoDTO> itens = pedido.getItens().stream()
                .map(item -> new ItemPedidoDTO(
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getSubTotal()))
                .collect(Collectors.toSet());

        return new PedidoDTO(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                itens);
    }


    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    }

}
