package com.api.pedido.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.api.pedido.domain.enums.EstadoPedido;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;

    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private EstadoPedido status;

    @OneToMany(mappedBy = "id.pedido", cascade =  CascadeType.ALL)
    private Set<ItemPedido> itens = new HashSet<>();
}
