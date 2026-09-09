package com.taller.ms_pedidos.model;

import com.taller.ms_pedidos.enums.EstadoPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbPedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El usuarioId es obligatorio")
    @Column(nullable = false)
    private Long usuarioId;

    @NotNull(message = "El productoId es obligatorio")
    @Column(nullable = false)
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    @Column(nullable = false)
    private Integer cantidad;

    @NotNull(message = "El total es obligatorio")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPedido estado;

    //OBLIGATORIO SIEMPRE PONER CONSTRUCTOR VACÍO
    public Pedido() {
        //REQUERIDO POR JPA/HIBERNATE
    }

    public Pedido(Long usuarioId, Long productoId, Integer cantidad, BigDecimal total,
                  LocalDateTime fecha, EstadoPedido estado) {
        this.usuarioId = usuarioId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }
}