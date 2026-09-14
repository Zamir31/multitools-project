package com.bankcore;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cliente cliente = new Cliente(1, "Daniel", "Noriega", "daniel@noriega.com", "1234567890");
        Cuenta cuenta = new Cuenta(1, "1234567890", new BigDecimal("1000.00"));
        cliente.addCuenta(cuenta);
        System.out.println("Cliente: " + cuenta.getCliente().getCompleteName());
        System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo: " + cuenta.getSaldo());
        cuenta.depositar(new BigDecimal("100.00"));
        System.out.println("Saldo: " + cuenta.getSaldo());
        cuenta.retirar(new BigDecimal("100.00"));
        System.out.println("Saldo: " + cuenta.getSaldo());
    }
}
