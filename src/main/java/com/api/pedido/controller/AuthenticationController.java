package com.api.pedido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pedido.domain.Usuario;
import com.api.pedido.domain.enums.UserRole;
import com.api.pedido.dtos.AuthenticationDTO;
import com.api.pedido.dtos.LoginResponseDTO;
import com.api.pedido.dtos.RegisterDTO;
import com.api.pedido.repository.UsuarioRepository;
import com.api.pedido.security.Jwt.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        

        Usuario usuario =
            (Usuario) auth.getPrincipal();

        var token = tokenService.generateToken((usuario));


        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getRole().name()));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data){

        if (this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.nome() ,data.email(), encryptedPassword, UserRole.CLIENTE);

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
