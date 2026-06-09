package com.api.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.Produto;
import com.api.pedido.dtos.ProdutoDTO;
import com.api.pedido.dtos.ProdutoRequestDTO;
import com.api.pedido.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Page<ProdutoDTO> listar(Pageable pageable) {
        return repository
            .findAll(pageable)
            .map(this::converterDTO);
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow();

        return converterDTO(produto);
    }

    public Page<ProdutoDTO> buscarPorNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable)
            .map(this::converterDTO);
    }

    public ProdutoDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setEstoque(dto.estoque());
        produto.setPreco(dto.preco());
        produto.setImagem(dto.imagem());

        Produto salvo = repository.save(produto);
        return converterDTO(salvo);
    }

    public ProdutoDTO editar(Long id, ProdutoRequestDTO produtoAtualizado) {

        Produto produto = repository.findById(id).orElseThrow();

        produto.setNome(produtoAtualizado.nome());
        produto.setPreco(produtoAtualizado.preco());
        produto.setEstoque(produtoAtualizado.estoque());
        produto.setImagem(produtoAtualizado.imagem());

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
            produto.getEstoque(),
            produto.getImagem()
        );
    }
}
