package com.api.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.api.pedido.dtos.CarrinhoDTO;
import com.api.pedido.dtos.ItemCarrinhoRequestDTO;
import com.api.pedido.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService service;

    @GetMapping("/meu")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<CarrinhoDTO> buscarMeuCarrinho() {

        return ResponseEntity.ok(
                service.buscarMeuCarrinho());
    }

    @PostMapping("/itens")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> adicionarItem(
            @RequestBody ItemCarrinhoRequestDTO dto) {

        service.adicionarItem(dto);

        return ResponseEntity.ok(
                "Item adicionado ao carrinho");
    }

    @DeleteMapping("/itens/{produtoId}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> removerItem(
            @PathVariable Long produtoId) {

        service.removerItem(produtoId);

        return ResponseEntity.ok(
                "Item removido do carrinho");
    }

    @PatchMapping("/itens/{produtoId}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> atualizarQuantidade(
            @PathVariable Long produtoId,
            @RequestParam Integer quantidade) {

        service.atualizarQuantidade(
                produtoId,
                quantidade);

        return ResponseEntity.ok(
                "Quantidade atualizada");
    }

    @DeleteMapping("/limpar")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> limparCarrinho() {

        service.limparCarrinho();

        return ResponseEntity.ok(
                "Carrinho limpo com sucesso");
    }
}