package com.curso.pedidos.repository;
 
import com.curso.pedidos.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
 
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
 
    @EntityGraph(attributePaths = {"cliente", "lineas"})
    List<Pedido> findByClienteId(Long clienteId);
 
    @EntityGraph(attributePaths = {"cliente"})
    @Override
    @NonNull
    List<Pedido> findAll();
}
