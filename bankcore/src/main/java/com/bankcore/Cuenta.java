package com.bankcore;

import java.math.BigDecimal;

public class Cuenta {
    private String titular = "Juan Perez";
    private String numeroCuenta = "1234567890";
    private BigDecimal saldo = new BigDecimal("1000.0");
    private BigDecimal limiteCredito = new BigDecimal("1000.0");
    private BigDecimal saldoBloqueado = new BigDecimal("0.0");
    private BigDecimal saldoPendiente = new BigDecimal("0.0");
    private boolean isBlocked = false;

    public Cuenta(String titular, BigDecimal saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
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

    public void depositar(BigDecimal cantidad) {
        if (isBlocked) {
            System.out.println("La cuenta está bloqueada");
            return;
        }
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.add(cantidad);
            System.out.println("Deposito realizado correctamente");
        } else {
            System.out.println("La cantidad a depositar debe ser mayor a 0");
        }
    }

    public void retirar(BigDecimal cantidad) {
        if (isBlocked) {
            System.out.println("La cuenta está bloqueada");
            return;
        }
        if (cantidad.compareTo(getSaldo()) > 0) {
            System.out.println("La cantidad a retirar es mayor al saldo disponible");
            return;
        }
        if (cantidad.compareTo(BigDecimal.ZERO) > 0) {
            saldo = saldo.subtract(cantidad);
            System.out.println("Retiro realizado correctamente");
        } else {
            System.out.println("La cantidad a retirar debe ser mayor a 0");
        }
    }
}
