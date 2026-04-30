package com.curso.pedidos.repository;

import com.curso.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de Pedidos — Estado INICIAL del taller.
 *
 * En el Paso 2 vas a agregar @EntityGraph para resolver el problema N+1.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByClienteId(Long clienteId);
}
