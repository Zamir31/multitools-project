package com.bankcore.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("AHORRO")
public class CuentaAhorro extends Cuenta {

    public CuentaAhorro(String numeroCuenta, BigDecimal saldo) {
        super(numeroCuenta, saldo);
    }

    protected CuentaAhorro() {}

    @Override 
    protected void debitar(BigDecimal cantidad) {
        if(cantidad.compareTo(new BigDecimal("100")) > 0) {
            throw new CuentaException("La cantidad a retirar es mayor a 100");
        }
        super.debitar(cantidad);
    }
}
