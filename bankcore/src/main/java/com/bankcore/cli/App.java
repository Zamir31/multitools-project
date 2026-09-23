package com.bankcore.cli;

import java.math.BigDecimal;

import com.bankcore.application.TransferirDinero;
import com.bankcore.domain.Cliente;
import com.bankcore.domain.CuentaAhorro;
import com.bankcore.domain.CuentaCorriente;
import com.bankcore.domain.CuentaException;
import com.bankcore.repository.CuentaRepository;
import com.bankcore.repository.memory.InMemoryCuentaRepository;

/**
 * CLI: crea datos de demo, cablea dependencias e imprime resultados.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Bank Core listo para usar");

        Cliente cliente = new Cliente(1, "Daniel", "Noriega", "daniel@noriega.com", "1234567890");

        CuentaAhorro cuentaAhorro = new CuentaAhorro(1, "1234567890", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaAhorro);

        CuentaCorriente cuentaCorriente = new CuentaCorriente(2, "1234567891", new BigDecimal("1000.00"));
        cliente.addCuenta(cuentaCorriente);

        CuentaRepository cuentaRepository = new InMemoryCuentaRepository();
        cuentaRepository.guardar(cuentaAhorro);
        cuentaRepository.guardar(cuentaCorriente);

        TransferirDinero transferirDinero = new TransferirDinero(cuentaRepository);

        try {
            transferirDinero.ejecutar("1234567891", "1234567890", new BigDecimal("100.00"));
            System.out.println("Transferencia realizada correctamente");
            System.out.println("Saldo corriente: " + cuentaCorriente.getSaldo());
            System.out.println("Saldo ahorro: " + cuentaAhorro.getSaldo());
            System.out.println("Historial corriente: " + cuentaCorriente.getHistorial());
            System.out.println("Historial ahorro: " + cuentaAhorro.getHistorial());
        } catch (CuentaException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Transferencia finalizada");
        }
    }
}
