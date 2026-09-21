package com.bankcore.cli;

import java.math.BigDecimal;

import com.bankcore.domain.Cliente;
import com.bankcore.domain.CuentaAhorro;
import com.bankcore.domain.CuentaCorriente;
import com.bankcore.domain.CuentaException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cliente cliente = new Cliente(1, "Daniel", "Noriega", "daniel@noriega.com", "1234567890");

        CuentaAhorro cuentaAhorro = new CuentaAhorro(1, "1234567890", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaAhorro);

        CuentaCorriente cuentaCorriente = new CuentaCorriente(2, "1234567891", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaCorriente);

        try {
            cuentaCorriente.transferir(cuentaAhorro, new BigDecimal("1500.00"));
            System.out.println("Transferencia realizada correctamente");
            System.out.println("Historial de transacciones: " + cuentaCorriente.getHistorial());
        } catch (CuentaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Transferencia finalizada");
        }
    }
}
