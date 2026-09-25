package com.bankcore.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("CORRIENTE")
public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(String numeroCuenta, BigDecimal saldo) {
        super(numeroCuenta, saldo);
    }

    protected CuentaCorriente() {}
}
