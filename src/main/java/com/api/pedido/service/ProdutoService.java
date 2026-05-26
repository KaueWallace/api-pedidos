package com.api.pedido.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.Produto;
import com.api.pedido.dtos.ProdutoDTO;
import com.api.pedido.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> listar() {
        List<Produto> lista = repository.findAll();
        
        return lista.stream()
            .map(this::converterDTO)
            .toList();

    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow();

        return converterDTO(produto);
    }

    public List<ProdutoDTO> buscarPorNome(String nome) {
        List<Produto> lista = repository.findByNomeContainingIgnoreCase(nome);

        return lista.stream()
            .map(this::converterDTO)
            .collect(Collectors.toList());
    }

    public ProdutoDTO salvar(Produto produto) {
        Produto salvo = repository.save(produto);
        return converterDTO(salvo);
    }

    public ProdutoDTO editar(Long id, Produto produtoAtualizado) {

        Produto produto = repository.findById(id).orElseThrow();

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setEstoque(produtoAtualizado.getEstoque());

        Produto salvo = repository.save(produto);

        return converterDTO(salvo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public ProdutoDTO converterDTO(Produto produto){
        return new ProdutoDTO(
            produto.getId(),
            produto.getNome(),
            produto.getPreco(),
            produto.getEstoque()
        );
    }
}
