package com.bankcore;

public class Cuenta {
    private String titular = "Juan Perez";
    private String numeroCuenta = "1234567890";
    private double saldo = 1000.0;
    private double limiteCredito = 1000.0;
    private double saldoBloqueado = 0.0;
    private double saldoPendiente = 0.0;
    private boolean isBlocked = false;

    public Cuenta(String titular, String numeroCuenta, double saldo) {
        this.titular = titular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public double getSaldo() {
        return saldo;
    }
    public double getLimiteCredito() {
        return limiteCredito;
    }
    public double getSaldoDisponible() {
        return saldo - limiteCredito;
    }
    public double getSaldoBloqueado() {
        return saldoBloqueado;
    }
    public double getSaldoPendiente() {
        return saldoPendiente;
    }
    public boolean isBlocked() {
        return isBlocked;
    }

    public void depositar(double cantidad) {
        if (isBlocked) {
            System.out.println("La cuenta está bloqueada");
            return;
        }
        if (cantidad > 0) {
            saldo += cantidad;
            System.out.println("Deposito realizado correctamente");
        } else {
            System.out.println("La cantidad a depositar debe ser mayor a 0");
        }
    }

    public void retirar(double cantidad) {
        if (isBlocked) {
            System.out.println("La cuenta está bloqueada");
            return;
        }
        if (cantidad > getSaldoDisponible()) {
            System.out.println("La cantidad a retirar es mayor al saldo disponible");
            return;
        }
        if (cantidad > 0) {
            saldo -= cantidad;
            System.out.println("Retiro realizado correctamente");
        } else {
            System.out.println("La cantidad a retirar debe ser mayor a 0");
        }
    }
}
