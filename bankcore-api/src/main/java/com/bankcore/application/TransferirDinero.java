package com.bankcore.application;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankcore.domain.Cuenta;
import com.bankcore.domain.CuentaException;
import com.bankcore.repository.CuentaRepository;

@Service
public class TransferirDinero {

    private final CuentaRepository cuentaRepository;

    public TransferirDinero(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public void ejecutar(String numeroCuentaOrigen, String numeroCuentaDestino, BigDecimal monto) {
        Cuenta cuentaOrigen = cuentaRepository.findByNumeroCuenta(numeroCuentaOrigen)
            .orElseThrow(() -> new CuentaException("Cuenta de origen no encontrada"));
        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(numeroCuentaDestino)
            .orElseThrow(() -> new CuentaException("Cuenta de destino no encontrada"));
        cuentaOrigen.transferir(cuentaDestino, monto);
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);
    }

}
