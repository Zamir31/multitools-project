package com.bankcore.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "cuentas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta")
public abstract class Cuenta {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255, unique = true)
    private String numeroCuenta;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo = new BigDecimal("1000.0");

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteCredito = new BigDecimal("1000.0");

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoBloqueado = new BigDecimal("0.0");

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal saldoPendiente = new BigDecimal("0.0");

    @Column(nullable = false)
    private boolean blocked = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "historial_transacciones", joinColumns = @JoinColumn(name = "cuenta_id"))
    private List<Transaccion> historial = new ArrayList<>();

    @Transient
    private Cliente cliente;

    protected Cuenta(String numeroCuenta, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    protected Cuenta() {}

    public Long getId() {
        return id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public BigDecimal getSaldoBloqueado() {
        return saldoBloqueado;
    }

    public BigDecimal getSaldoPendiente() {
        return saldoPendiente;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Transaccion> getHistorial() {
        return List.copyOf(historial);
    }

    private void acreditar(BigDecimal cantidad) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CuentaException("La cantidad a acreditar debe ser mayor a 0");
        }
        if (blocked) {
            throw new CuentaException("La cuenta está bloqueada");
        }
        saldo = saldo.add(cantidad);
    }

    protected void debitar(BigDecimal cantidad) {

        if (cantidad.compareTo(getSaldo()) > 0) {
            throw new CuentaException("La cantidad a retirar es mayor al saldo de la cuenta");
        }
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CuentaException("La cantidad a debitar debe ser mayor a 0");
        }
        if (blocked) {
            throw new CuentaException("La cuenta está bloqueada");
        }
        saldo = saldo.subtract(cantidad);
    }

    public void depositar(BigDecimal cantidad) {
        acreditar(cantidad);
        agregarTransaccion(new Transaccion(TipoTransaccion.DEPOSITO, cantidad, LocalDateTime.now(), "Deposito de " + cantidad, this.getNumeroCuenta(), null));
    }

    public void retirar(BigDecimal cantidad) {
        debitar(cantidad);
        agregarTransaccion(new Transaccion(TipoTransaccion.RETIRO, cantidad, LocalDateTime.now(), "Retiro de " + cantidad, this.getNumeroCuenta(), null));
    }

    public void transferir(Cuenta cuentaDestino, BigDecimal cantidad) {
        if (cuentaDestino == null) {
            throw new CuentaException("La cuenta destino no puede ser null");
        }
        if (cuentaDestino.isBlocked()) {
            throw new CuentaException("La cuenta destino está bloqueada");
        }
        if (cuentaDestino.equals(this)) {
            throw new CuentaException("La cuenta destino no puede ser la misma cuenta");
        }
        debitar(cantidad);
        agregarTransaccion(new Transaccion(TipoTransaccion.TRANSFERENCIA_ENVIADA, cantidad, LocalDateTime.now(), "Transferencia a " + cuentaDestino.getNumeroCuenta(), this.getNumeroCuenta(), cuentaDestino.getNumeroCuenta()));
        cuentaDestino.acreditar(cantidad);
        cuentaDestino.agregarTransaccion(new Transaccion(TipoTransaccion.TRANSFERENCIA_RECIBIDA, cantidad, LocalDateTime.now(), "Transferencia de " + this.getNumeroCuenta(), this.getNumeroCuenta(), cuentaDestino.getNumeroCuenta()));
    }

    void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    void desasignarCliente() {
        this.cliente = null;
    }

    public void bloquearCuenta() {
        this.blocked = true;
    }

    public void desbloquearCuenta() {
        this.blocked = false;
    }

    private void agregarTransaccion(Transaccion transaccion) {
        historial.add(transaccion);
        System.out.println("Transaccion agregada correctamente");
    }
}
