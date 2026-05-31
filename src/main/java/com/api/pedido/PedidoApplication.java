package com.api.pedido;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.pedido.domain.Usuario;
import com.api.pedido.domain.enums.UserRole;
import com.api.pedido.repository.UsuarioRepository;

@SpringBootApplication
public class PedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoApplication.class, args);
	}

	@Bean
	CommandLineRunner initAdmin(
			UsuarioRepository repository,
			PasswordEncoder encoder) {

		return args -> {
			if (repository.findByEmail("admin@email.com") == null) {

				Usuario admin = new Usuario(
						"admin@email.com",
						encoder.encode("admin123"),
						UserRole.ADMIN);

				admin.setNome("Admin");

				repository.save(admin);
			}

			if (repository.findByEmail("cliente@email.com") == null) {

				Usuario cliente = new Usuario(
						"cliente@email.com",
						encoder.encode("cliente123"),
						UserRole.CLIENTE);

				cliente.setNome("Cliente Teste");

				repository.save(cliente);
			}
		};
	}

}
