package com.curso.pedidos.entity;

import com.curso.pedidos.dto.LineaPedidoDTO;
import com.curso.pedidos.dto.PedidoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // Evita ciclo en serialización temporalmente, hasta que se cree el DTO
    private List<LineaPedido> lineas;

    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;

    private String observaciones;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public PedidoDTO toDTO() {
        return new PedidoDTO(
                this.id,
                this.numero,
                this.fechaCreacion,
                this.total,
                this.cliente != null ? this.cliente.getNombre() : null,
                this.lineas != null
                        ? this.lineas.stream()
                                .map(l -> new LineaPedidoDTO(
                                        l.getId(),
                                        l.getDescripcion(),
                                        l.getCantidad(),
                                        l.getPrecioUnitario()))
                                .toList()
                        : List.of());
    }

}