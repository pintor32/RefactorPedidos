package com.curso.pedidos.controller;

import com.curso.pedidos.entity.Pedido;
import com.curso.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de Pedidos — Estado INICIAL del taller (con problemas).
 *
 * Problemas que vas a corregir paso a paso:
 *  - Devuelve la entidad directamente (Paso 1: usar DTO)
 *  - Usa @Autowired (Paso 3: inyección por constructor)
 *  - Tiene lógica de negocio que debería estar en un servicio (Paso 3)
 *  - El loop p.getLineas().size() esconde un N+1 (Paso 2)
 *  - Sin @Transactional (Paso 3)
 *
 * NO modifiques esta clase hasta que el taller te indique el paso.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepo;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoRepo.findAll();
    }

    @GetMapping("/cliente/{id}")
    public List<Pedido> porCliente(@PathVariable Long id) {
        List<Pedido> pedidos = pedidoRepo.findByClienteId(id);
        for (Pedido p : pedidos) {
            p.getLineas().size(); // forzar carga (esto dispara N+1)
        }
        return pedidos;
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoRepo.deleteById(id);
    }
}
