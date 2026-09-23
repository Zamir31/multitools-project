package com.bankcore.repository.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bankcore.domain.Cuenta;
import com.bankcore.repository.CuentaRepository;

/**
 * Adaptador en memoria: guarda cuentas en un Map.
 * Más adelante podrías tener FileCuentaRepository sin cambiar TransferirDinero.
 */
public class InMemoryCuentaRepository implements CuentaRepository {

    private final Map<String, Cuenta> cuentasPorNumero = new HashMap<>();

    @Override
    public void guardar(Cuenta cuenta) {
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no puede ser null");
        }
        if (cuenta.getNumeroCuenta() == null || cuenta.getNumeroCuenta().isBlank()) {
            throw new IllegalArgumentException("La cuenta debe tener número");
        }
        cuentasPorNumero.put(cuenta.getNumeroCuenta(), cuenta);
    }

    @Override
    public Optional<Cuenta> buscarPorNumero(String numeroCuenta) {
        return Optional.ofNullable(cuentasPorNumero.get(numeroCuenta));
    }

    @Override
    public List<Cuenta> listar() {
        return List.copyOf(new ArrayList<>(cuentasPorNumero.values()));
    }
}
