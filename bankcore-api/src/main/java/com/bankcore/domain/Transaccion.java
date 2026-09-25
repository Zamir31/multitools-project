package com.bankcore.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;

@Embeddable
public class Transaccion {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransaccion tipoTransaccion;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal cantidad;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 255)
    private String numeroCuentaOrigen;

    @Column(length = 255)
    private String numeroCuentaDestino;

    public Transaccion(TipoTransaccion tipoTransaccion, BigDecimal cantidad, LocalDateTime fecha, String descripcion, String numeroCuentaOrigen, String numeroCuentaDestino) {
        this.tipoTransaccion = tipoTransaccion;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    protected Transaccion() {}

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

}
