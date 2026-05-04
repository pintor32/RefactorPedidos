package com.curso.pedidos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
 
public record PedidoDTO(
    Long id,
    String numero,
    LocalDateTime fechaCreacion,
    BigDecimal total,
    String clienteNombre,
    List<LineaPedidoDTO> lineas
) {}