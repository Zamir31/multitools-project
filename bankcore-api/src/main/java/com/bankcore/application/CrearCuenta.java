package com.bankcore.application;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankcore.domain.Cuenta;
import com.bankcore.domain.CuentaAhorro;
import com.bankcore.domain.CuentaCorriente;
import com.bankcore.domain.CuentaException;
import com.bankcore.domain.TipoCuenta;
import com.bankcore.repository.CuentaRepository;


@Service 
public class CrearCuenta {

    private final CuentaRepository cuentaRepository;

    public CrearCuenta(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public Cuenta ejecutar(TipoCuenta tipoCuenta, String numeroCuenta, BigDecimal saldo){
        if(cuentaRepository.existsByNumeroCuenta(numeroCuenta)) {
            throw new CuentaException("La cuenta ya existe");
        }
        Cuenta cuenta = switch(tipoCuenta) {
            case AHORRO -> new CuentaAhorro(numeroCuenta, saldo);
            case CORRIENTE -> new CuentaCorriente(numeroCuenta, saldo);
        };
        
        return cuentaRepository.save(cuenta);
    }

}
