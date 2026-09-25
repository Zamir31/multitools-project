package com.bankcore.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankcore.domain.Cuenta;
import com.bankcore.domain.CuentaException;
import com.bankcore.repository.CuentaRepository;

@Service
public class ConsultarCuenta {

    private final CuentaRepository cuentaRepository;

    public ConsultarCuenta(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional(readOnly = true)
    public Cuenta ejecutar(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
            .orElseThrow(() -> new CuentaException("Cuenta" + numeroCuenta + "no encontrada"));
    }
}
