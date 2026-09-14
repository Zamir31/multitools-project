package com.bankcore;

import java.math.BigDecimal;

public class CuentaAhorro extends Cuenta {

    public CuentaAhorro(int id, String numeroCuenta, BigDecimal saldo) {
        super(id, numeroCuenta, saldo);
    }

    @Override
    public void retirar(BigDecimal cantidad) {
        if(cantidad.compareTo(new BigDecimal("100")) > 0) {
            System.out.println("La cantidad a retirar es mayor a 100");
            return;
        } else {
            super.retirar(cantidad);
        }
    }
}
