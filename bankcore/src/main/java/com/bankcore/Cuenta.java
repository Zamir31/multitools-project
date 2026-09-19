package com.bankcore;

import java.math.BigDecimal;

public abstract class Cuenta {
    private int id;
    private String numeroCuenta;
    private BigDecimal saldo = new BigDecimal("1000.0");
    private BigDecimal limiteCredito = new BigDecimal("1000.0");
    private BigDecimal saldoBloqueado = new BigDecimal("0.0");
    private BigDecimal saldoPendiente = new BigDecimal("0.0");
    private boolean isBlocked = false;

    private Cliente cliente;

    public Cuenta(int id, String numeroCuenta, BigDecimal saldo) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public int getId() {
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
        return isBlocked;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(BigDecimal cantidad) {
        if (isBlocked) {
            throw new CuentaException("La cuenta está bloqueada");
        }
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(cantidad);
            System.out.println("Deposito realizado correctamente");
        } else {
            throw new CuentaException("La cantidad a depositar debe ser mayor a 0");
        }
    }

    public void retirar(BigDecimal cantidad) {
        if (isBlocked) {
            throw new CuentaException("La cuenta está bloqueada");
        }
        if (cantidad.compareTo(getSaldo()) > 0) {
            throw new CuentaException("La cantidad a retirar es mayor al saldo disponible");
        }
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Retiro realizado correctamente");
        } else {
            throw new CuentaException("La cantidad a retirar debe ser mayor a 0");
        }
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
        this.retirar(cantidad);
        cuentaDestino.depositar(cantidad);
        System.out.println("Transferencia realizada correctamente");
    }
    
    void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    void desasignarCliente() {
        this.cliente = null;
    }

    public void bloquearCuenta() {
        this.isBlocked = true;
    }

    public void desbloquearCuenta() {
        this.isBlocked = false;
    }
}

