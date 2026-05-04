package com.curso.pedidos.dto;

import java.math.BigDecimal;
 
public record LineaPedidoDTO(
    Long id,
    String descripcion,
    Integer cantidad,
    BigDecimal precioUnitario
) {}