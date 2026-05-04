package com.curso.pedidos.controller;

import com.curso.pedidos.dto.PedidoDTO;
import com.curso.pedidos.entity.Pedido;
import com.curso.pedidos.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDTO> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/cliente/{id}")
    public List<PedidoDTO> porCliente(@PathVariable Long id) {
        return pedidoService.porCliente(id);
    }

    @PostMapping
    public PedidoDTO crear(@RequestBody Pedido pedido) {
        return pedidoService.crear(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
