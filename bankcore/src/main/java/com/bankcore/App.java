package com.bankcore;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cuenta cuenta = new Cuenta("Juan Perez", "1234567890", 1000.0);
        cuenta.depositar(100.0);
        System.out.println("Titular: " + cuenta.getTitular());
        System.out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
        System.out.println("Saldo: " + cuenta.getSaldo());
        cuenta.retirar(100.0);
        System.out.println("Saldo: " + cuenta.getSaldo());
    }
}
