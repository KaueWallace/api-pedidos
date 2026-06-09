package com.api.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.pedido.domain.enums.EstadoPedido;
import com.api.pedido.dtos.AtualizarStatusDTO;
import com.api.pedido.dtos.PedidoDTO;
import com.api.pedido.dtos.PedidoRequestDTO;
import com.api.pedido.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PedidoDTO>> listarTodos(
            @RequestParam(required = false) EstadoPedido status, Pageable pageable) {

        if (status != null) {
            return ResponseEntity.ok(
                    service
                            .listarTodosPedidosPorStatus(
                                    status, pageable));
        }

        return ResponseEntity.ok(
                service.listar(pageable));
    }

    @GetMapping("/meus")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Page<PedidoDTO>> listarMeusPedidos(
            @RequestParam(required = false) EstadoPedido status, Pageable pageable) {

        if (status != null) {
            return ResponseEntity.ok(service.listarMeusPedidosPorStatus(status, pageable));
        }

        return ResponseEntity.ok(
                service.listarMeusPedidos(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/meu/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PedidoDTO> buscarMeuPedido(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarMeuPedidoPorId(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PedidoDTO> salvar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PedidoDTO> atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusDTO dto) {
        return ResponseEntity.ok(service.atualizarStatus(id, dto));
    }

    @PatchMapping("/meus/{id}/cancelar")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        service.cancelarPedido(id);
        return ResponseEntity.ok("Pedido cancelado com sucesso");
    }

}
