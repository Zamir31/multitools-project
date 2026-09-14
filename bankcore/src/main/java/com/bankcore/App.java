package com.bankcore;

import java.math.BigDecimal;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cuenta cuenta = new Cuenta("Daniel Noriega", new BigDecimal("1000.00"));
        cuenta.depositar(new BigDecimal("100.00"));
        System.out.println("Titular: " + cuenta.getTitular());
        System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo: " + cuenta.getSaldo());
        cuenta.retirar(new BigDecimal("100.00"));
        System.out.println("Saldo: " + cuenta.getSaldo());
    }
}
