package com.api.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.api.pedido.dtos.EnderecoCadastroDTO;
import com.api.pedido.dtos.EnderecoDTO;
import com.api.pedido.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnderecoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/meus")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Page<EnderecoDTO>> listarMeusEnderecos(Pageable pageable) {

        return ResponseEntity.ok(
                service.listarMeusEnderecos(pageable));

    }

    @GetMapping("/meus/todos")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<List<EnderecoDTO>> listarTodosMeusEnderecos() {

        return ResponseEntity.ok(
                service.listarTodosMeusEnderecos());

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<EnderecoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorID(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<EnderecoDTO> cadastrar(@RequestBody EnderecoCadastroDTO dados) {

        EnderecoDTO enderecoDTO = service.cadastrar(dados);

        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<EnderecoDTO> editar(@PathVariable Long id, @RequestBody EnderecoCadastroDTO dados) {
        return ResponseEntity.ok(service.editar(id, dados));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
