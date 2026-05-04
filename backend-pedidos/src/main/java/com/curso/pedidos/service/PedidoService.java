package com.curso.pedidos.service;

import com.curso.pedidos.dto.PedidoDTO;
import com.curso.pedidos.entity.Pedido;
import com.curso.pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;

    public PedidoService(PedidoRepository pedidoRepo) {
        this.pedidoRepo = pedidoRepo;
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarTodos() {
        return pedidoRepo.findAll().stream()
                .map(Pedido::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> porCliente(Long clienteId) {
        return pedidoRepo.findByClienteId(clienteId).stream()
                .map(Pedido::toDTO)
                .toList();
    }

    @Transactional
    public PedidoDTO crear(Pedido pedido) {
        Pedido guardado = pedidoRepo.save(pedido);
        return guardado.toDTO();
    }

    @Transactional
    public void eliminar(Long id) {
        pedidoRepo.deleteById(id);
    }
}
