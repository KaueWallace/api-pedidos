package com.api.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.pedido.domain.Endereco;
import com.api.pedido.domain.Usuario;
import com.api.pedido.dtos.EnderecoCadastroDTO;
import com.api.pedido.dtos.EnderecoDTO;
import com.api.pedido.dtos.UsuarioResumoDTO;
import com.api.pedido.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<EnderecoDTO> listar(){
        List<Endereco> enderecos = repository.findAll();

        List<EnderecoDTO> dto = enderecos.stream().map(this::converterDTO).toList();

        return dto;
    }


    public EnderecoDTO buscarPorID(Long id){
        Endereco endereco = repository.findById(id).orElseThrow();

        return converterDTO(endereco);
    }


    public EnderecoDTO cadastrar(EnderecoCadastroDTO dto){
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


    public EnderecoDTO editar(Long id, EnderecoCadastroDTO enderecoDTO){
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

    private EnderecoDTO converterDTO(Endereco endereco){

        UsuarioResumoDTO usuario = new UsuarioResumoDTO(
            endereco.getUsuario().getId(),
            endereco.getUsuario().getNome(),
            endereco.getUsuario().getEmail()
        );

        EnderecoDTO dto = new EnderecoDTO(
            endereco.getId(),
            endereco.getCep(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getBairro(),
            endereco.getCidade(),
            endereco.getEstado()
        );

        return dto;
    }


    private Usuario getUsuarioLogado() {
        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
