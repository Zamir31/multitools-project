package com.bankcore;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cliente cliente = new Cliente(1, "Daniel", "Noriega", "daniel@noriega.com", "1234567890");

        CuentaCorriente cuentaCorriente = new CuentaCorriente(1, "1234567890", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaCorriente);

        CuentaAhorro cuentaAhorro = new CuentaAhorro(2, "1234567891", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaAhorro);

        System.out.println("Saldo cuenta corriente: " + cuentaCorriente.getSaldo());
        cuentaCorriente.bloquearCuenta();

        cuentaCorriente.depositar(new BigDecimal("100.00"));
    }
}
