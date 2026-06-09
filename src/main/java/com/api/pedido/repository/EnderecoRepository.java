package com.api.pedido.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.pedido.domain.Endereco;
import com.api.pedido.domain.Usuario;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByIdAndUsuario(Long id, Usuario usuario);
    Page<Endereco> findByUsuarioId(Long usuarioId, Pageable pageable);
    List<Endereco> findByUsuarioId(Long usuarioId);
}
