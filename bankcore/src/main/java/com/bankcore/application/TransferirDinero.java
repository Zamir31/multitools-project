package com.bankcore.application;

import java.math.BigDecimal;

import com.bankcore.domain.Cuenta;
import com.bankcore.domain.CuentaException;
import com.bankcore.repository.CuentaRepository;

/**
 * Caso de uso: orquesta una transferencia.
 * No conoce System.out ni el Map interno del repositorio.
 */
public class TransferirDinero {

    private final CuentaRepository cuentas;

    public TransferirDinero(CuentaRepository cuentas) {
        this.cuentas = cuentas;
    }

    public void ejecutar(String numeroOrigen, String numeroDestino, BigDecimal monto) {
        Cuenta origen = cuentas.buscarPorNumero(numeroOrigen)
                .orElseThrow(() -> new CuentaException("Cuenta origen no encontrada: " + numeroOrigen));

        Cuenta destino = cuentas.buscarPorNumero(numeroDestino)
                .orElseThrow(() -> new CuentaException("Cuenta destino no encontrada: " + numeroDestino));

        origen.transferir(destino, monto);

        // En memoria es la misma referencia; guardar deja claro el ciclo "cargar → mutar → persistir"
        cuentas.guardar(origen);
        cuentas.guardar(destino);
    }
}
