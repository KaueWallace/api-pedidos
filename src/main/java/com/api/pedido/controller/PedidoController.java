package com.api.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PedidoDTO> listar(){
        return service.listar();
    }

    @GetMapping("/meus")
    @PreAuthorize("hasRole('CLIENTE')")
    public List<PedidoDTO> listarMeusPedidos() {
        return service.listarMeusPedidos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public PedidoDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public PedidoDTO salvar(@RequestBody PedidoRequestDTO dto){
        return service.salvar(dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public PedidoDTO atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusDTO dto){
        return service.atualizarStatus(id, dto);
    }

}
