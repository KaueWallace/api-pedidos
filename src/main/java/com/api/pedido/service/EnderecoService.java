package com.api.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.Endereco;
import com.api.pedido.domain.Usuario;
import com.api.pedido.dtos.EnderecoCadastroDTO;
import com.api.pedido.dtos.EnderecoDTO;
import com.api.pedido.repository.EnderecoRepository;
import com.api.pedido.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<EnderecoDTO> listar() {
        List<Endereco> enderecos = repository.findAll();

        List<EnderecoDTO> dto = enderecos.stream().map(this::converterDTO).toList();

        return dto;
    }

    @Transactional
    public Page<EnderecoDTO> listarMeusEnderecos(Pageable pageable) {

        Usuario usuario = getUsuarioLogado();

        return repository
                .findByUsuarioId(usuario.getId(), pageable)
                .map(this::converterDTO);
    }

    public EnderecoDTO buscarPorID(Long id) {
        Endereco endereco = repository.findById(id).orElseThrow();

        return converterDTO(endereco);
    }

    public EnderecoDTO cadastrar(EnderecoCadastroDTO dto) {
        Endereco endereco = new Endereco();

        Usuario usuario = getUsuarioLogado();

        endereco.setCep(dto.cep());
        endereco.setNumero(dto.numero());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setComplemento(dto.complemento());
        endereco.setEstado(dto.estado());
        endereco.setUsuario(usuario);

        Endereco salvo = repository.save(endereco);

        return converterDTO(salvo);
    }

    public EnderecoDTO editar(Long id, EnderecoCadastroDTO enderecoDTO) {
        Endereco endereco = repository.findById(id).orElseThrow();

        endereco.setCep(enderecoDTO.cep());
        endereco.setNumero(enderecoDTO.numero());
        endereco.setComplemento(enderecoDTO.complemento());
        endereco.setBairro(enderecoDTO.bairro());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setEstado(enderecoDTO.estado());

        Endereco atualizado = repository.save(endereco);

        return converterDTO(atualizado);

    }

    @Transactional
    public void excluir(Long id) {

        Usuario usuarioLogado = getUsuarioLogado();

        Endereco endereco = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));

        boolean possuiPedidos = pedidoRepository.existsByEnderecoId(id);

        if (possuiPedidos) {
            throw new RuntimeException(
                    "Não é possível excluir um endereço vinculado a pedidos");
        }

        if (!endereco.getUsuario().getId().equals(usuarioLogado.getId())) {
            throw new RuntimeException("Você não pode excluir este endereço");
        }

        repository.delete(endereco);
    }

    private EnderecoDTO converterDTO(Endereco endereco) {

        EnderecoDTO dto = new EnderecoDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado());

        return dto;
    }

    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
