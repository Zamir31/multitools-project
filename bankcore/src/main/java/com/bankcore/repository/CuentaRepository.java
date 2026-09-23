package com.bankcore.repository;

import java.util.List;
import java.util.Optional;

import com.bankcore.domain.Cuenta;

/**
 * Puerto de persistencia de cuentas.
 * La aplicación depende de esta abstracción, no de cómo se guardan los datos.
 */
public interface CuentaRepository {

    void guardar(Cuenta cuenta);

    Optional<Cuenta> buscarPorNumero(String numeroCuenta);

    List<Cuenta> listar();
}
