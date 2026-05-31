package com.api.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.pedido.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    UserDetails findByEmail(String email);
}
