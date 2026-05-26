package com.api.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pedido.dtos.PedidoDTO;
import com.api.pedido.dtos.PedidoRequestDTO;
import com.api.pedido.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoDTO> listar(){
        return service.listar();
    }

    @PostMapping
    public PedidoDTO salvar(@RequestBody PedidoRequestDTO dto){
        return service.salvar(dto);
    }
}
