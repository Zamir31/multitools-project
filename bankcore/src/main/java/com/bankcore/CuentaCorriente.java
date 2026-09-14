package com.bankcore;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(int id, String numeroCuenta, BigDecimal saldo) {
        super(id, numeroCuenta, saldo);
    }
}
